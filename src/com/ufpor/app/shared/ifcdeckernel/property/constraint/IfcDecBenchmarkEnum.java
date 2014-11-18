package com.ufpor.app.shared.ifcdeckernel.property.constraint;

import com.ufpor.app.shared.ifcclient.constraint.IfcClientBenchmarkEnum;

/**
 * Created by Ehsan Barekati on 11/18/14.
 */
public enum IfcDecBenchmarkEnum {
    GREATERTHAN,
    GREATERTHANOREQUALTO,
    LESSTHAN,
    LESSTHANOREQUALTO,
    EQUALTO,
    NOTEQUALTO,
    INCLUDES,
    NOTINCLUDES,
    INCLUDEDIN,
    NOTINCLUDEDIN;

    public static IfcDecBenchmarkEnum get(IfcClientBenchmarkEnum client) {
        for  (IfcDecBenchmarkEnum en : IfcDecBenchmarkEnum.values()) {
            if (en.name().equals(client.name())) {
                return en;
            }
        }
        return null;
    }

    public static IfcDecBenchmarkEnum getInstance(IfcClientBenchmarkEnum benchMark) {
        for(IfcDecBenchmarkEnum en : IfcDecBenchmarkEnum.values()) {
            if (en.name().equals(benchMark.name())) {
                return en;
            }
        }
        return null;
    }
}
