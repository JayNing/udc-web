/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.common.body;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2018-05-25")
public class ModuleInfo implements org.apache.thrift.TBase<ModuleInfo, ModuleInfo._Fields>, java.io.Serializable, Cloneable, Comparable<ModuleInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ModuleInfo");

  private static final org.apache.thrift.protocol.TField MODULE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("moduleId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PARENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("parentId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField MODULE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("moduleName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField MODULE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("moduleCode", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("createTime", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField FLAG_FIELD_DESC = new org.apache.thrift.protocol.TField("flag", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField SUB_MODEL_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("subModelList", org.apache.thrift.protocol.TType.LIST, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ModuleInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ModuleInfoTupleSchemeFactory();

  public int moduleId; // required
  public int parentId; // required
  public String moduleName; // required
  public String moduleCode; // required
  public long createTime; // required
  public int flag; // required
  public java.util.List<ModuleInfo> subModelList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MODULE_ID((short)1, "moduleId"),
    PARENT_ID((short)2, "parentId"),
    MODULE_NAME((short)3, "moduleName"),
    MODULE_CODE((short)4, "moduleCode"),
    CREATE_TIME((short)5, "createTime"),
    FLAG((short)6, "flag"),
    SUB_MODEL_LIST((short)7, "subModelList");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

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
        case 1: // MODULE_ID
          return MODULE_ID;
        case 2: // PARENT_ID
          return PARENT_ID;
        case 3: // MODULE_NAME
          return MODULE_NAME;
        case 4: // MODULE_CODE
          return MODULE_CODE;
        case 5: // CREATE_TIME
          return CREATE_TIME;
        case 6: // FLAG
          return FLAG;
        case 7: // SUB_MODEL_LIST
          return SUB_MODEL_LIST;
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
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __MODULEID_ISSET_ID = 0;
  private static final int __PARENTID_ISSET_ID = 1;
  private static final int __CREATETIME_ISSET_ID = 2;
  private static final int __FLAG_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MODULE_ID, new org.apache.thrift.meta_data.FieldMetaData("moduleId", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PARENT_ID, new org.apache.thrift.meta_data.FieldMetaData("parentId", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MODULE_NAME, new org.apache.thrift.meta_data.FieldMetaData("moduleName", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MODULE_CODE, new org.apache.thrift.meta_data.FieldMetaData("moduleCode", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("createTime", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FLAG, new org.apache.thrift.meta_data.FieldMetaData("flag", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SUB_MODEL_LIST, new org.apache.thrift.meta_data.FieldMetaData("subModelList", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "ModuleInfo"))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ModuleInfo.class, metaDataMap);
  }

  public ModuleInfo() {
  }

  public ModuleInfo(
    int moduleId,
    int parentId,
    String moduleName,
    String moduleCode,
    long createTime,
    int flag,
    java.util.List<ModuleInfo> subModelList)
  {
    this();
    this.moduleId = moduleId;
    setModuleIdIsSet(true);
    this.parentId = parentId;
    setParentIdIsSet(true);
    this.moduleName = moduleName;
    this.moduleCode = moduleCode;
    this.createTime = createTime;
    setCreateTimeIsSet(true);
    this.flag = flag;
    setFlagIsSet(true);
    this.subModelList = subModelList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ModuleInfo(ModuleInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.moduleId = other.moduleId;
    this.parentId = other.parentId;
    if (other.isSetModuleName()) {
      this.moduleName = other.moduleName;
    }
    if (other.isSetModuleCode()) {
      this.moduleCode = other.moduleCode;
    }
    this.createTime = other.createTime;
    this.flag = other.flag;
    if (other.isSetSubModelList()) {
      java.util.List<ModuleInfo> __this__subModelList = new java.util.ArrayList<ModuleInfo>(other.subModelList.size());
      for (ModuleInfo other_element : other.subModelList) {
        __this__subModelList.add(other_element);
      }
      this.subModelList = __this__subModelList;
    }
  }

  public ModuleInfo deepCopy() {
    return new ModuleInfo(this);
  }

  @Override
  public void clear() {
    setModuleIdIsSet(false);
    this.moduleId = 0;
    setParentIdIsSet(false);
    this.parentId = 0;
    this.moduleName = null;
    this.moduleCode = null;
    setCreateTimeIsSet(false);
    this.createTime = 0;
    setFlagIsSet(false);
    this.flag = 0;
    this.subModelList = null;
  }

  public int getModuleId() {
    return this.moduleId;
  }

  public ModuleInfo setModuleId(int moduleId) {
    this.moduleId = moduleId;
    setModuleIdIsSet(true);
    return this;
  }

  public void unsetModuleId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MODULEID_ISSET_ID);
  }

  /** Returns true if field moduleId is set (has been assigned a value) and false otherwise */
  public boolean isSetModuleId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MODULEID_ISSET_ID);
  }

  public void setModuleIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MODULEID_ISSET_ID, value);
  }

  public int getParentId() {
    return this.parentId;
  }

  public ModuleInfo setParentId(int parentId) {
    this.parentId = parentId;
    setParentIdIsSet(true);
    return this;
  }

  public void unsetParentId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  /** Returns true if field parentId is set (has been assigned a value) and false otherwise */
  public boolean isSetParentId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  public void setParentIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PARENTID_ISSET_ID, value);
  }

  public String getModuleName() {
    return this.moduleName;
  }

  public ModuleInfo setModuleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  public void unsetModuleName() {
    this.moduleName = null;
  }

  /** Returns true if field moduleName is set (has been assigned a value) and false otherwise */
  public boolean isSetModuleName() {
    return this.moduleName != null;
  }

  public void setModuleNameIsSet(boolean value) {
    if (!value) {
      this.moduleName = null;
    }
  }

  public String getModuleCode() {
    return this.moduleCode;
  }

  public ModuleInfo setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
    return this;
  }

  public void unsetModuleCode() {
    this.moduleCode = null;
  }

  /** Returns true if field moduleCode is set (has been assigned a value) and false otherwise */
  public boolean isSetModuleCode() {
    return this.moduleCode != null;
  }

  public void setModuleCodeIsSet(boolean value) {
    if (!value) {
      this.moduleCode = null;
    }
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public ModuleInfo setCreateTime(long createTime) {
    this.createTime = createTime;
    setCreateTimeIsSet(true);
    return this;
  }

  public void unsetCreateTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  /** Returns true if field createTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  public void setCreateTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CREATETIME_ISSET_ID, value);
  }

  public int getFlag() {
    return this.flag;
  }

  public ModuleInfo setFlag(int flag) {
    this.flag = flag;
    setFlagIsSet(true);
    return this;
  }

  public void unsetFlag() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FLAG_ISSET_ID);
  }

  /** Returns true if field flag is set (has been assigned a value) and false otherwise */
  public boolean isSetFlag() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FLAG_ISSET_ID);
  }

  public void setFlagIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FLAG_ISSET_ID, value);
  }

  public int getSubModelListSize() {
    return (this.subModelList == null) ? 0 : this.subModelList.size();
  }

  public java.util.Iterator<ModuleInfo> getSubModelListIterator() {
    return (this.subModelList == null) ? null : this.subModelList.iterator();
  }

  public void addToSubModelList(ModuleInfo elem) {
    if (this.subModelList == null) {
      this.subModelList = new java.util.ArrayList<ModuleInfo>();
    }
    this.subModelList.add(elem);
  }

  public java.util.List<ModuleInfo> getSubModelList() {
    return this.subModelList;
  }

  public ModuleInfo setSubModelList(java.util.List<ModuleInfo> subModelList) {
    this.subModelList = subModelList;
    return this;
  }

  public void unsetSubModelList() {
    this.subModelList = null;
  }

  /** Returns true if field subModelList is set (has been assigned a value) and false otherwise */
  public boolean isSetSubModelList() {
    return this.subModelList != null;
  }

  public void setSubModelListIsSet(boolean value) {
    if (!value) {
      this.subModelList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MODULE_ID:
      if (value == null) {
        unsetModuleId();
      } else {
        setModuleId((Integer)value);
      }
      break;

    case PARENT_ID:
      if (value == null) {
        unsetParentId();
      } else {
        setParentId((Integer)value);
      }
      break;

    case MODULE_NAME:
      if (value == null) {
        unsetModuleName();
      } else {
        setModuleName((String)value);
      }
      break;

    case MODULE_CODE:
      if (value == null) {
        unsetModuleCode();
      } else {
        setModuleCode((String)value);
      }
      break;

    case CREATE_TIME:
      if (value == null) {
        unsetCreateTime();
      } else {
        setCreateTime((Long)value);
      }
      break;

    case FLAG:
      if (value == null) {
        unsetFlag();
      } else {
        setFlag((Integer)value);
      }
      break;

    case SUB_MODEL_LIST:
      if (value == null) {
        unsetSubModelList();
      } else {
        setSubModelList((java.util.List<ModuleInfo>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MODULE_ID:
      return getModuleId();

    case PARENT_ID:
      return getParentId();

    case MODULE_NAME:
      return getModuleName();

    case MODULE_CODE:
      return getModuleCode();

    case CREATE_TIME:
      return getCreateTime();

    case FLAG:
      return getFlag();

    case SUB_MODEL_LIST:
      return getSubModelList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MODULE_ID:
      return isSetModuleId();
    case PARENT_ID:
      return isSetParentId();
    case MODULE_NAME:
      return isSetModuleName();
    case MODULE_CODE:
      return isSetModuleCode();
    case CREATE_TIME:
      return isSetCreateTime();
    case FLAG:
      return isSetFlag();
    case SUB_MODEL_LIST:
      return isSetSubModelList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ModuleInfo)
      return this.equals((ModuleInfo)that);
    return false;
  }

  public boolean equals(ModuleInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_moduleId = true;
    boolean that_present_moduleId = true;
    if (this_present_moduleId || that_present_moduleId) {
      if (!(this_present_moduleId && that_present_moduleId))
        return false;
      if (this.moduleId != that.moduleId)
        return false;
    }

    boolean this_present_parentId = true;
    boolean that_present_parentId = true;
    if (this_present_parentId || that_present_parentId) {
      if (!(this_present_parentId && that_present_parentId))
        return false;
      if (this.parentId != that.parentId)
        return false;
    }

    boolean this_present_moduleName = true && this.isSetModuleName();
    boolean that_present_moduleName = true && that.isSetModuleName();
    if (this_present_moduleName || that_present_moduleName) {
      if (!(this_present_moduleName && that_present_moduleName))
        return false;
      if (!this.moduleName.equals(that.moduleName))
        return false;
    }

    boolean this_present_moduleCode = true && this.isSetModuleCode();
    boolean that_present_moduleCode = true && that.isSetModuleCode();
    if (this_present_moduleCode || that_present_moduleCode) {
      if (!(this_present_moduleCode && that_present_moduleCode))
        return false;
      if (!this.moduleCode.equals(that.moduleCode))
        return false;
    }

    boolean this_present_createTime = true;
    boolean that_present_createTime = true;
    if (this_present_createTime || that_present_createTime) {
      if (!(this_present_createTime && that_present_createTime))
        return false;
      if (this.createTime != that.createTime)
        return false;
    }

    boolean this_present_flag = true;
    boolean that_present_flag = true;
    if (this_present_flag || that_present_flag) {
      if (!(this_present_flag && that_present_flag))
        return false;
      if (this.flag != that.flag)
        return false;
    }

    boolean this_present_subModelList = true && this.isSetSubModelList();
    boolean that_present_subModelList = true && that.isSetSubModelList();
    if (this_present_subModelList || that_present_subModelList) {
      if (!(this_present_subModelList && that_present_subModelList))
        return false;
      if (!this.subModelList.equals(that.subModelList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + moduleId;

    hashCode = hashCode * 8191 + parentId;

    hashCode = hashCode * 8191 + ((isSetModuleName()) ? 131071 : 524287);
    if (isSetModuleName())
      hashCode = hashCode * 8191 + moduleName.hashCode();

    hashCode = hashCode * 8191 + ((isSetModuleCode()) ? 131071 : 524287);
    if (isSetModuleCode())
      hashCode = hashCode * 8191 + moduleCode.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(createTime);

    hashCode = hashCode * 8191 + flag;

    hashCode = hashCode * 8191 + ((isSetSubModelList()) ? 131071 : 524287);
    if (isSetSubModelList())
      hashCode = hashCode * 8191 + subModelList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ModuleInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetModuleId()).compareTo(other.isSetModuleId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModuleId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.moduleId, other.moduleId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParentId()).compareTo(other.isSetParentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parentId, other.parentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetModuleName()).compareTo(other.isSetModuleName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModuleName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.moduleName, other.moduleName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetModuleCode()).compareTo(other.isSetModuleCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModuleCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.moduleCode, other.moduleCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreateTime()).compareTo(other.isSetCreateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createTime, other.createTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlag()).compareTo(other.isSetFlag());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlag()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flag, other.flag);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSubModelList()).compareTo(other.isSetSubModelList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubModelList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subModelList, other.subModelList);
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
  public String toString() {
    StringBuilder sb = new StringBuilder("ModuleInfo(");
    boolean first = true;

    sb.append("moduleId:");
    sb.append(this.moduleId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("parentId:");
    sb.append(this.parentId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("moduleName:");
    if (this.moduleName == null) {
      sb.append("null");
    } else {
      sb.append(this.moduleName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("moduleCode:");
    if (this.moduleCode == null) {
      sb.append("null");
    } else {
      sb.append(this.moduleCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("createTime:");
    sb.append(this.createTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("flag:");
    sb.append(this.flag);
    first = false;
    if (!first) sb.append(", ");
    sb.append("subModelList:");
    if (this.subModelList == null) {
      sb.append("null");
    } else {
      sb.append(this.subModelList);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ModuleInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ModuleInfoStandardScheme getScheme() {
      return new ModuleInfoStandardScheme();
    }
  }

  private static class ModuleInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<ModuleInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ModuleInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MODULE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.moduleId = iprot.readI32();
              struct.setModuleIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PARENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.parentId = iprot.readI32();
              struct.setParentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MODULE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.moduleName = iprot.readString();
              struct.setModuleNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MODULE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.moduleCode = iprot.readString();
              struct.setModuleCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CREATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTime = iprot.readI64();
              struct.setCreateTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // FLAG
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.flag = iprot.readI32();
              struct.setFlagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // SUB_MODEL_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.subModelList = new java.util.ArrayList<ModuleInfo>(_list0.size);
                ModuleInfo _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new ModuleInfo();
                  _elem1.read(iprot);
                  struct.subModelList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSubModelListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ModuleInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MODULE_ID_FIELD_DESC);
      oprot.writeI32(struct.moduleId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PARENT_ID_FIELD_DESC);
      oprot.writeI32(struct.parentId);
      oprot.writeFieldEnd();
      if (struct.moduleName != null) {
        oprot.writeFieldBegin(MODULE_NAME_FIELD_DESC);
        oprot.writeString(struct.moduleName);
        oprot.writeFieldEnd();
      }
      if (struct.moduleCode != null) {
        oprot.writeFieldBegin(MODULE_CODE_FIELD_DESC);
        oprot.writeString(struct.moduleCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CREATE_TIME_FIELD_DESC);
      oprot.writeI64(struct.createTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(FLAG_FIELD_DESC);
      oprot.writeI32(struct.flag);
      oprot.writeFieldEnd();
      if (struct.subModelList != null) {
        oprot.writeFieldBegin(SUB_MODEL_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.subModelList.size()));
          for (ModuleInfo _iter3 : struct.subModelList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ModuleInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ModuleInfoTupleScheme getScheme() {
      return new ModuleInfoTupleScheme();
    }
  }

  private static class ModuleInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<ModuleInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ModuleInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetModuleId()) {
        optionals.set(0);
      }
      if (struct.isSetParentId()) {
        optionals.set(1);
      }
      if (struct.isSetModuleName()) {
        optionals.set(2);
      }
      if (struct.isSetModuleCode()) {
        optionals.set(3);
      }
      if (struct.isSetCreateTime()) {
        optionals.set(4);
      }
      if (struct.isSetFlag()) {
        optionals.set(5);
      }
      if (struct.isSetSubModelList()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetModuleId()) {
        oprot.writeI32(struct.moduleId);
      }
      if (struct.isSetParentId()) {
        oprot.writeI32(struct.parentId);
      }
      if (struct.isSetModuleName()) {
        oprot.writeString(struct.moduleName);
      }
      if (struct.isSetModuleCode()) {
        oprot.writeString(struct.moduleCode);
      }
      if (struct.isSetCreateTime()) {
        oprot.writeI64(struct.createTime);
      }
      if (struct.isSetFlag()) {
        oprot.writeI32(struct.flag);
      }
      if (struct.isSetSubModelList()) {
        {
          oprot.writeI32(struct.subModelList.size());
          for (ModuleInfo _iter4 : struct.subModelList)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ModuleInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.moduleId = iprot.readI32();
        struct.setModuleIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.parentId = iprot.readI32();
        struct.setParentIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.moduleName = iprot.readString();
        struct.setModuleNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.moduleCode = iprot.readString();
        struct.setModuleCodeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.createTime = iprot.readI64();
        struct.setCreateTimeIsSet(true);
      }
      if (incoming.get(5)) {
        struct.flag = iprot.readI32();
        struct.setFlagIsSet(true);
      }
      if (incoming.get(6)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.subModelList = new java.util.ArrayList<ModuleInfo>(_list5.size);
          ModuleInfo _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new ModuleInfo();
            _elem6.read(iprot);
            struct.subModelList.add(_elem6);
          }
        }
        struct.setSubModelListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

