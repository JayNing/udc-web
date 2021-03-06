/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.common.body;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * 系统日志消息, 自行定义部分需要形成文档
 * 
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2018-05-18")
public class SystemLogMessage implements org.apache.thrift.TBase<SystemLogMessage, SystemLogMessage._Fields>, java.io.Serializable, Cloneable, Comparable<SystemLogMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SystemLogMessage");

  private static final org.apache.thrift.protocol.TField SYSTEM_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("systemCode", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField USER_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("userInfo", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField OPRATION_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("oprationTime", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField LOG_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("logType", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField LOG_CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("logContent", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField ATTRS_FIELD_DESC = new org.apache.thrift.protocol.TField("attrs", org.apache.thrift.protocol.TType.MAP, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SystemLogMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SystemLogMessageTupleSchemeFactory();

  public java.lang.String systemCode; // required
  public UserInfo userInfo; // required
  public long oprationTime; // required
  public int logType; // required
  public java.lang.String logContent; // required
  public java.util.Map<java.lang.String,java.lang.String> attrs; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SYSTEM_CODE((short)1, "systemCode"),
    USER_INFO((short)2, "userInfo"),
    OPRATION_TIME((short)3, "oprationTime"),
    LOG_TYPE((short)4, "logType"),
    LOG_CONTENT((short)5, "logContent"),
    ATTRS((short)6, "attrs");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SYSTEM_CODE
          return SYSTEM_CODE;
        case 2: // USER_INFO
          return USER_INFO;
        case 3: // OPRATION_TIME
          return OPRATION_TIME;
        case 4: // LOG_TYPE
          return LOG_TYPE;
        case 5: // LOG_CONTENT
          return LOG_CONTENT;
        case 6: // ATTRS
          return ATTRS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __OPRATIONTIME_ISSET_ID = 0;
  private static final int __LOGTYPE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SYSTEM_CODE, new org.apache.thrift.meta_data.FieldMetaData("systemCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.USER_INFO, new org.apache.thrift.meta_data.FieldMetaData("userInfo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, UserInfo.class)));
    tmpMap.put(_Fields.OPRATION_TIME, new org.apache.thrift.meta_data.FieldMetaData("oprationTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LOG_TYPE, new org.apache.thrift.meta_data.FieldMetaData("logType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LOG_CONTENT, new org.apache.thrift.meta_data.FieldMetaData("logContent", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ATTRS, new org.apache.thrift.meta_data.FieldMetaData("attrs", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SystemLogMessage.class, metaDataMap);
  }

  public SystemLogMessage() {
  }

  public SystemLogMessage(
    java.lang.String systemCode,
    UserInfo userInfo,
    long oprationTime,
    int logType,
    java.lang.String logContent,
    java.util.Map<java.lang.String,java.lang.String> attrs)
  {
    this();
    this.systemCode = systemCode;
    this.userInfo = userInfo;
    this.oprationTime = oprationTime;
    setOprationTimeIsSet(true);
    this.logType = logType;
    setLogTypeIsSet(true);
    this.logContent = logContent;
    this.attrs = attrs;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SystemLogMessage(SystemLogMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetSystemCode()) {
      this.systemCode = other.systemCode;
    }
    if (other.isSetUserInfo()) {
      this.userInfo = new UserInfo(other.userInfo);
    }
    this.oprationTime = other.oprationTime;
    this.logType = other.logType;
    if (other.isSetLogContent()) {
      this.logContent = other.logContent;
    }
    if (other.isSetAttrs()) {
      java.util.Map<java.lang.String,java.lang.String> __this__attrs = new java.util.HashMap<java.lang.String,java.lang.String>(other.attrs);
      this.attrs = __this__attrs;
    }
  }

  public SystemLogMessage deepCopy() {
    return new SystemLogMessage(this);
  }

  @Override
  public void clear() {
    this.systemCode = null;
    this.userInfo = null;
    setOprationTimeIsSet(false);
    this.oprationTime = 0;
    setLogTypeIsSet(false);
    this.logType = 0;
    this.logContent = null;
    this.attrs = null;
  }

  public java.lang.String getSystemCode() {
    return this.systemCode;
  }

  public SystemLogMessage setSystemCode(java.lang.String systemCode) {
    this.systemCode = systemCode;
    return this;
  }

  public void unsetSystemCode() {
    this.systemCode = null;
  }

  /** Returns true if field systemCode is set (has been assigned a value) and false otherwise */
  public boolean isSetSystemCode() {
    return this.systemCode != null;
  }

  public void setSystemCodeIsSet(boolean value) {
    if (!value) {
      this.systemCode = null;
    }
  }

  public UserInfo getUserInfo() {
    return this.userInfo;
  }

  public SystemLogMessage setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  public void unsetUserInfo() {
    this.userInfo = null;
  }

  /** Returns true if field userInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetUserInfo() {
    return this.userInfo != null;
  }

  public void setUserInfoIsSet(boolean value) {
    if (!value) {
      this.userInfo = null;
    }
  }

  public long getOprationTime() {
    return this.oprationTime;
  }

  public SystemLogMessage setOprationTime(long oprationTime) {
    this.oprationTime = oprationTime;
    setOprationTimeIsSet(true);
    return this;
  }

  public void unsetOprationTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __OPRATIONTIME_ISSET_ID);
  }

  /** Returns true if field oprationTime is set (has been assigned a value) and false otherwise */
  public boolean isSetOprationTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __OPRATIONTIME_ISSET_ID);
  }

  public void setOprationTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __OPRATIONTIME_ISSET_ID, value);
  }

  public int getLogType() {
    return this.logType;
  }

  public SystemLogMessage setLogType(int logType) {
    this.logType = logType;
    setLogTypeIsSet(true);
    return this;
  }

  public void unsetLogType() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOGTYPE_ISSET_ID);
  }

  /** Returns true if field logType is set (has been assigned a value) and false otherwise */
  public boolean isSetLogType() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOGTYPE_ISSET_ID);
  }

  public void setLogTypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOGTYPE_ISSET_ID, value);
  }

  public java.lang.String getLogContent() {
    return this.logContent;
  }

  public SystemLogMessage setLogContent(java.lang.String logContent) {
    this.logContent = logContent;
    return this;
  }

  public void unsetLogContent() {
    this.logContent = null;
  }

  /** Returns true if field logContent is set (has been assigned a value) and false otherwise */
  public boolean isSetLogContent() {
    return this.logContent != null;
  }

  public void setLogContentIsSet(boolean value) {
    if (!value) {
      this.logContent = null;
    }
  }

  public int getAttrsSize() {
    return (this.attrs == null) ? 0 : this.attrs.size();
  }

  public void putToAttrs(java.lang.String key, java.lang.String val) {
    if (this.attrs == null) {
      this.attrs = new java.util.HashMap<java.lang.String,java.lang.String>();
    }
    this.attrs.put(key, val);
  }

  public java.util.Map<java.lang.String,java.lang.String> getAttrs() {
    return this.attrs;
  }

  public SystemLogMessage setAttrs(java.util.Map<java.lang.String,java.lang.String> attrs) {
    this.attrs = attrs;
    return this;
  }

  public void unsetAttrs() {
    this.attrs = null;
  }

  /** Returns true if field attrs is set (has been assigned a value) and false otherwise */
  public boolean isSetAttrs() {
    return this.attrs != null;
  }

  public void setAttrsIsSet(boolean value) {
    if (!value) {
      this.attrs = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SYSTEM_CODE:
      if (value == null) {
        unsetSystemCode();
      } else {
        setSystemCode((java.lang.String)value);
      }
      break;

    case USER_INFO:
      if (value == null) {
        unsetUserInfo();
      } else {
        setUserInfo((UserInfo)value);
      }
      break;

    case OPRATION_TIME:
      if (value == null) {
        unsetOprationTime();
      } else {
        setOprationTime((java.lang.Long)value);
      }
      break;

    case LOG_TYPE:
      if (value == null) {
        unsetLogType();
      } else {
        setLogType((java.lang.Integer)value);
      }
      break;

    case LOG_CONTENT:
      if (value == null) {
        unsetLogContent();
      } else {
        setLogContent((java.lang.String)value);
      }
      break;

    case ATTRS:
      if (value == null) {
        unsetAttrs();
      } else {
        setAttrs((java.util.Map<java.lang.String,java.lang.String>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SYSTEM_CODE:
      return getSystemCode();

    case USER_INFO:
      return getUserInfo();

    case OPRATION_TIME:
      return getOprationTime();

    case LOG_TYPE:
      return getLogType();

    case LOG_CONTENT:
      return getLogContent();

    case ATTRS:
      return getAttrs();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SYSTEM_CODE:
      return isSetSystemCode();
    case USER_INFO:
      return isSetUserInfo();
    case OPRATION_TIME:
      return isSetOprationTime();
    case LOG_TYPE:
      return isSetLogType();
    case LOG_CONTENT:
      return isSetLogContent();
    case ATTRS:
      return isSetAttrs();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof SystemLogMessage)
      return this.equals((SystemLogMessage)that);
    return false;
  }

  public boolean equals(SystemLogMessage that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_systemCode = true && this.isSetSystemCode();
    boolean that_present_systemCode = true && that.isSetSystemCode();
    if (this_present_systemCode || that_present_systemCode) {
      if (!(this_present_systemCode && that_present_systemCode))
        return false;
      if (!this.systemCode.equals(that.systemCode))
        return false;
    }

    boolean this_present_userInfo = true && this.isSetUserInfo();
    boolean that_present_userInfo = true && that.isSetUserInfo();
    if (this_present_userInfo || that_present_userInfo) {
      if (!(this_present_userInfo && that_present_userInfo))
        return false;
      if (!this.userInfo.equals(that.userInfo))
        return false;
    }

    boolean this_present_oprationTime = true;
    boolean that_present_oprationTime = true;
    if (this_present_oprationTime || that_present_oprationTime) {
      if (!(this_present_oprationTime && that_present_oprationTime))
        return false;
      if (this.oprationTime != that.oprationTime)
        return false;
    }

    boolean this_present_logType = true;
    boolean that_present_logType = true;
    if (this_present_logType || that_present_logType) {
      if (!(this_present_logType && that_present_logType))
        return false;
      if (this.logType != that.logType)
        return false;
    }

    boolean this_present_logContent = true && this.isSetLogContent();
    boolean that_present_logContent = true && that.isSetLogContent();
    if (this_present_logContent || that_present_logContent) {
      if (!(this_present_logContent && that_present_logContent))
        return false;
      if (!this.logContent.equals(that.logContent))
        return false;
    }

    boolean this_present_attrs = true && this.isSetAttrs();
    boolean that_present_attrs = true && that.isSetAttrs();
    if (this_present_attrs || that_present_attrs) {
      if (!(this_present_attrs && that_present_attrs))
        return false;
      if (!this.attrs.equals(that.attrs))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSystemCode()) ? 131071 : 524287);
    if (isSetSystemCode())
      hashCode = hashCode * 8191 + systemCode.hashCode();

    hashCode = hashCode * 8191 + ((isSetUserInfo()) ? 131071 : 524287);
    if (isSetUserInfo())
      hashCode = hashCode * 8191 + userInfo.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(oprationTime);

    hashCode = hashCode * 8191 + logType;

    hashCode = hashCode * 8191 + ((isSetLogContent()) ? 131071 : 524287);
    if (isSetLogContent())
      hashCode = hashCode * 8191 + logContent.hashCode();

    hashCode = hashCode * 8191 + ((isSetAttrs()) ? 131071 : 524287);
    if (isSetAttrs())
      hashCode = hashCode * 8191 + attrs.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(SystemLogMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSystemCode()).compareTo(other.isSetSystemCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSystemCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.systemCode, other.systemCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserInfo()).compareTo(other.isSetUserInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userInfo, other.userInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOprationTime()).compareTo(other.isSetOprationTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOprationTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.oprationTime, other.oprationTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLogType()).compareTo(other.isSetLogType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLogType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.logType, other.logType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLogContent()).compareTo(other.isSetLogContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLogContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.logContent, other.logContent);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAttrs()).compareTo(other.isSetAttrs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAttrs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.attrs, other.attrs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SystemLogMessage(");
    boolean first = true;

    sb.append("systemCode:");
    if (this.systemCode == null) {
      sb.append("null");
    } else {
      sb.append(this.systemCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userInfo:");
    if (this.userInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.userInfo);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("oprationTime:");
    sb.append(this.oprationTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("logType:");
    sb.append(this.logType);
    first = false;
    if (!first) sb.append(", ");
    sb.append("logContent:");
    if (this.logContent == null) {
      sb.append("null");
    } else {
      sb.append(this.logContent);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("attrs:");
    if (this.attrs == null) {
      sb.append("null");
    } else {
      sb.append(this.attrs);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (userInfo != null) {
      userInfo.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SystemLogMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SystemLogMessageStandardScheme getScheme() {
      return new SystemLogMessageStandardScheme();
    }
  }

  private static class SystemLogMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<SystemLogMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SystemLogMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SYSTEM_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.systemCode = iprot.readString();
              struct.setSystemCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.userInfo = new UserInfo();
              struct.userInfo.read(iprot);
              struct.setUserInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // OPRATION_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.oprationTime = iprot.readI64();
              struct.setOprationTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // LOG_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.logType = iprot.readI32();
              struct.setLogTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LOG_CONTENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.logContent = iprot.readString();
              struct.setLogContentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ATTRS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map42 = iprot.readMapBegin();
                struct.attrs = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map42.size);
                java.lang.String _key43;
                java.lang.String _val44;
                for (int _i45 = 0; _i45 < _map42.size; ++_i45)
                {
                  _key43 = iprot.readString();
                  _val44 = iprot.readString();
                  struct.attrs.put(_key43, _val44);
                }
                iprot.readMapEnd();
              }
              struct.setAttrsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SystemLogMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.systemCode != null) {
        oprot.writeFieldBegin(SYSTEM_CODE_FIELD_DESC);
        oprot.writeString(struct.systemCode);
        oprot.writeFieldEnd();
      }
      if (struct.userInfo != null) {
        oprot.writeFieldBegin(USER_INFO_FIELD_DESC);
        struct.userInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(OPRATION_TIME_FIELD_DESC);
      oprot.writeI64(struct.oprationTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOG_TYPE_FIELD_DESC);
      oprot.writeI32(struct.logType);
      oprot.writeFieldEnd();
      if (struct.logContent != null) {
        oprot.writeFieldBegin(LOG_CONTENT_FIELD_DESC);
        oprot.writeString(struct.logContent);
        oprot.writeFieldEnd();
      }
      if (struct.attrs != null) {
        oprot.writeFieldBegin(ATTRS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.attrs.size()));
          for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter46 : struct.attrs.entrySet())
          {
            oprot.writeString(_iter46.getKey());
            oprot.writeString(_iter46.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SystemLogMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SystemLogMessageTupleScheme getScheme() {
      return new SystemLogMessageTupleScheme();
    }
  }

  private static class SystemLogMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<SystemLogMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SystemLogMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSystemCode()) {
        optionals.set(0);
      }
      if (struct.isSetUserInfo()) {
        optionals.set(1);
      }
      if (struct.isSetOprationTime()) {
        optionals.set(2);
      }
      if (struct.isSetLogType()) {
        optionals.set(3);
      }
      if (struct.isSetLogContent()) {
        optionals.set(4);
      }
      if (struct.isSetAttrs()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetSystemCode()) {
        oprot.writeString(struct.systemCode);
      }
      if (struct.isSetUserInfo()) {
        struct.userInfo.write(oprot);
      }
      if (struct.isSetOprationTime()) {
        oprot.writeI64(struct.oprationTime);
      }
      if (struct.isSetLogType()) {
        oprot.writeI32(struct.logType);
      }
      if (struct.isSetLogContent()) {
        oprot.writeString(struct.logContent);
      }
      if (struct.isSetAttrs()) {
        {
          oprot.writeI32(struct.attrs.size());
          for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter47 : struct.attrs.entrySet())
          {
            oprot.writeString(_iter47.getKey());
            oprot.writeString(_iter47.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SystemLogMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.systemCode = iprot.readString();
        struct.setSystemCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.userInfo = new UserInfo();
        struct.userInfo.read(iprot);
        struct.setUserInfoIsSet(true);
      }
      if (incoming.get(2)) {
        struct.oprationTime = iprot.readI64();
        struct.setOprationTimeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.logType = iprot.readI32();
        struct.setLogTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.logContent = iprot.readString();
        struct.setLogContentIsSet(true);
      }
      if (incoming.get(5)) {
        {
          org.apache.thrift.protocol.TMap _map48 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.attrs = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map48.size);
          java.lang.String _key49;
          java.lang.String _val50;
          for (int _i51 = 0; _i51 < _map48.size; ++_i51)
          {
            _key49 = iprot.readString();
            _val50 = iprot.readString();
            struct.attrs.put(_key49, _val50);
          }
        }
        struct.setAttrsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

