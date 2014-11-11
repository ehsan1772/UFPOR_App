package com.ufpor.app.shared.ifcclient;

/**
 * Created by Ehsan Barekati on 11/9/14.
 */
public class IfcClientSimplePropertyTemplate extends IfcClientPropertyTemplate {
public static enum IfcClientSimplePropertyTemplateTypeEnum {
    P_SINGLEVALUE,
    P_ENUMERATEDVALUE,
    P_BOUNDEDVALUE,
    P_LISTVALUE,
    P_TABLEVALUE,
    P_REFERENCEVALUE,
    Q_LENGTH,
    Q_AREA,
    Q_VOLUME,
    Q_COUNT,
    Q_WEIGHT,
    Q_TIME
}
private IfcClientSimplePropertyTemplateTypeEnum templateType;
    private IfcClientLabel primaryMeasuring;
    private IfcClientLabel secondaryMeasuringType;
    private IfcClientPropertyEnumenration enumerators;
    private IfcClientUnit primaryUnit;
    private IfcClientUnit secondaryUnit;
    private IfcClientLabel expression;
    private IfcClientStateEnum accessState;

}
