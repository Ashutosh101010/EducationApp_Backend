package com.aurd.security;

import com.aurd.Student.Model.Entity.Session;
import com.aurd.Student.Repository.SessionRepository;
import com.aurd.security.constant.TokenDescription;
import com.aurd.security.constant.ValidateCodes;
import com.google.gson.Gson;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

@ApplicationScoped
public class Security {

    @ConfigProperty(name = "tokenExpiry")
    Long tokenExpiry;


    @Inject
    SessionRepository sessionRepository;

    @Inject
    ManagedExecutor managedExecutor;

    public Token generateToken(Long userId,String userType)
    {
        Token token=new Token();

        token.setUserId(userId);
        token.setExpiry(System.currentTimeMillis()+tokenExpiry);
        token.setTimestamp(System.currentTimeMillis());
        token.setUserType(userType);
        Gson gson=new Gson();
        String stringToken=gson.toJson(token);
        String encodedString=Base64.getEncoder().encodeToString(stringToken.getBytes(StandardCharsets.UTF_8));
        token.setStringToken(encodedString);
        return token;

    }

    public ValidatedToken validateToken(String stringToken, ArrayList<String> roles)
    {
ValidatedToken validatedToken=new ValidatedToken();
if(stringToken==null || stringToken.isEmpty())
{
    validatedToken.setValid(false);
    validatedToken.setValidInvalidCode(ValidateCodes.TOKEN_EMPTY);
    validatedToken.setValidDescription(TokenDescription.TOKEN_EMPTY_DESCRIPTION);
    return validatedToken;
}

String decodedToken= new String(Base64.getDecoder().decode(stringToken),StandardCharsets.UTF_8);
Token token=new Gson().fromJson(decodedToken,Token.class);

Session session=sessionRepository.fetchSession(token.getUserId(),token.getUserType());

if(session==null)
{
    validatedToken.setValid(false);
    validatedToken.setValidInvalidCode(ValidateCodes.TOKEN_NOT_FOUND);
    validatedToken.setValidDescription(TokenDescription.TOKEN_NOT_FOUND_DESCRIPTION);
    return validatedToken;
}

        if(session.getExpiry()<=System.currentTimeMillis())
        {
            validatedToken.setValid(false);
            validatedToken.setValidInvalidCode(ValidateCodes.TOKEN_EXPIRED);
            validatedToken.setValidDescription(TokenDescription.TOKEN_EXPIRED_DESCRIPTION);
            return validatedToken;
        }
        if(!roles.contains(session.getUserType()))
        {
            validatedToken.setValid(false);
            validatedToken.setValidInvalidCode(ValidateCodes.TOKEN_USER_NOT_ALLOWED);
            validatedToken.setValidDescription(TokenDescription.TOKEN_USER_NOT_ALLOWED_DESCRIPTION);
            return validatedToken;
        }

        validatedToken.setValid(true);
        validatedToken.setValidDescription(TokenDescription.TOKEN_VALID_DESCRIPTION);
        validatedToken.setValidInvalidCode(ValidateCodes.TOKEN_VALID_CODE);

        sessionRepository.updateExpiry(stringToken);
        return validatedToken;

    }


//    public Token decodeToken(String stringToken)
//    {
//        Gson gson=new Gson();
//        String decodedStringToken =new String(Base64.getDecoder().decode(stringToken));
//        Token token=gson.fromJson(decodedStringToken,Token.class);
//        return token;
//    }
}
