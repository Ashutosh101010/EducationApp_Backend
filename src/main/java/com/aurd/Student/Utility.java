package com.aurd.Student;

import com.aurd.Student.Constant.Enums;

public class Utility {

    public static Boolean isValidEntityEnum(String type)
    {
        Boolean exists=false;
        for (Enums.EntityType entity: Enums.EntityType.values()) {
            if(entity.name().equals(type))
            {
                exists=true;
            }
        }
        return exists;
    }
}
