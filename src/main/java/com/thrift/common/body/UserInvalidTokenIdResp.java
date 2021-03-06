/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.common.body;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2018-05-18")
public class UserInvalidTokenIdResp implements org.apache.thrift.TBase<UserInvalidTokenIdResp, UserInvalidTokenIdResp._Fields>, java.io.Serializable, Cloneable, Comparable<UserInvalidTokenIdResp> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UserInvalidTokenIdResp");

  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField TOKEN_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("tokenIdList", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new UserInvalidTokenIdRespStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new UserInvalidTokenIdRespTupleSchemeFactory();

  public ResponseInfo response; // required
  public java.util.List<java.lang.String> tokenIdList; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESPONSE((short)1, "response"),
    TOKEN_ID_LIST((short)2, "tokenIdList");

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
        case 1: // RESPONSE
          return RESPONSE;
        case 2: // TOKEN_ID_LIST
          return TOKEN_ID_LIST;
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
  private static final _Fields optionals[] = {_Fields.TOKEN_ID_LIST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ResponseInfo.class)));
    tmpMap.put(_Fields.TOKEN_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("tokenIdList", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UserInvalidTokenIdResp.class, metaDataMap);
  }

  public UserInvalidTokenIdResp() {
  }

  public UserInvalidTokenIdResp(
    ResponseInfo response)
  {
    this();
    this.response = response;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UserInvalidTokenIdResp(UserInvalidTokenIdResp other) {
    if (other.isSetResponse()) {
      this.response = new ResponseInfo(other.response);
    }
    if (other.isSetTokenIdList()) {
      java.util.List<java.lang.String> __this__tokenIdList = new java.util.ArrayList<java.lang.String>(other.tokenIdList);
      this.tokenIdList = __this__tokenIdList;
    }
  }

  public UserInvalidTokenIdResp deepCopy() {
    return new UserInvalidTokenIdResp(this);
  }

  @Override
  public void clear() {
    this.response = null;
    this.tokenIdList = null;
  }

  public ResponseInfo getResponse() {
    return this.response;
  }

  public UserInvalidTokenIdResp setResponse(ResponseInfo response) {
    this.response = response;
    return this;
  }

  public void unsetResponse() {
    this.response = null;
  }

  /** Returns true if field response is set (has been assigned a value) and false otherwise */
  public boolean isSetResponse() {
    return this.response != null;
  }

  public void setResponseIsSet(boolean value) {
    if (!value) {
      this.response = null;
    }
  }

  public int getTokenIdListSize() {
    return (this.tokenIdList == null) ? 0 : this.tokenIdList.size();
  }

  public java.util.Iterator<java.lang.String> getTokenIdListIterator() {
    return (this.tokenIdList == null) ? null : this.tokenIdList.iterator();
  }

  public void addToTokenIdList(java.lang.String elem) {
    if (this.tokenIdList == null) {
      this.tokenIdList = new java.util.ArrayList<java.lang.String>();
    }
    this.tokenIdList.add(elem);
  }

  public java.util.List<java.lang.String> getTokenIdList() {
    return this.tokenIdList;
  }

  public UserInvalidTokenIdResp setTokenIdList(java.util.List<java.lang.String> tokenIdList) {
    this.tokenIdList = tokenIdList;
    return this;
  }

  public void unsetTokenIdList() {
    this.tokenIdList = null;
  }

  /** Returns true if field tokenIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetTokenIdList() {
    return this.tokenIdList != null;
  }

  public void setTokenIdListIsSet(boolean value) {
    if (!value) {
      this.tokenIdList = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((ResponseInfo)value);
      }
      break;

    case TOKEN_ID_LIST:
      if (value == null) {
        unsetTokenIdList();
      } else {
        setTokenIdList((java.util.List<java.lang.String>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case RESPONSE:
      return getResponse();

    case TOKEN_ID_LIST:
      return getTokenIdList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case RESPONSE:
      return isSetResponse();
    case TOKEN_ID_LIST:
      return isSetTokenIdList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof UserInvalidTokenIdResp)
      return this.equals((UserInvalidTokenIdResp)that);
    return false;
  }

  public boolean equals(UserInvalidTokenIdResp that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_response = true && this.isSetResponse();
    boolean that_present_response = true && that.isSetResponse();
    if (this_present_response || that_present_response) {
      if (!(this_present_response && that_present_response))
        return false;
      if (!this.response.equals(that.response))
        return false;
    }

    boolean this_present_tokenIdList = true && this.isSetTokenIdList();
    boolean that_present_tokenIdList = true && that.isSetTokenIdList();
    if (this_present_tokenIdList || that_present_tokenIdList) {
      if (!(this_present_tokenIdList && that_present_tokenIdList))
        return false;
      if (!this.tokenIdList.equals(that.tokenIdList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    hashCode = hashCode * 8191 + ((isSetTokenIdList()) ? 131071 : 524287);
    if (isSetTokenIdList())
      hashCode = hashCode * 8191 + tokenIdList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(UserInvalidTokenIdResp other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetResponse()).compareTo(other.isSetResponse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResponse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.response, other.response);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTokenIdList()).compareTo(other.isSetTokenIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTokenIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tokenIdList, other.tokenIdList);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("UserInvalidTokenIdResp(");
    boolean first = true;

    sb.append("response:");
    if (this.response == null) {
      sb.append("null");
    } else {
      sb.append(this.response);
    }
    first = false;
    if (isSetTokenIdList()) {
      if (!first) sb.append(", ");
      sb.append("tokenIdList:");
      if (this.tokenIdList == null) {
        sb.append("null");
      } else {
        sb.append(this.tokenIdList);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (response == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'response' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (response != null) {
      response.validate();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class UserInvalidTokenIdRespStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UserInvalidTokenIdRespStandardScheme getScheme() {
      return new UserInvalidTokenIdRespStandardScheme();
    }
  }

  private static class UserInvalidTokenIdRespStandardScheme extends org.apache.thrift.scheme.StandardScheme<UserInvalidTokenIdResp> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UserInvalidTokenIdResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESPONSE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.response = new ResponseInfo();
              struct.response.read(iprot);
              struct.setResponseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOKEN_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list26 = iprot.readListBegin();
                struct.tokenIdList = new java.util.ArrayList<java.lang.String>(_list26.size);
                java.lang.String _elem27;
                for (int _i28 = 0; _i28 < _list26.size; ++_i28)
                {
                  _elem27 = iprot.readString();
                  struct.tokenIdList.add(_elem27);
                }
                iprot.readListEnd();
              }
              struct.setTokenIdListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, UserInvalidTokenIdResp struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.tokenIdList != null) {
        if (struct.isSetTokenIdList()) {
          oprot.writeFieldBegin(TOKEN_ID_LIST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.tokenIdList.size()));
            for (java.lang.String _iter29 : struct.tokenIdList)
            {
              oprot.writeString(_iter29);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UserInvalidTokenIdRespTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UserInvalidTokenIdRespTupleScheme getScheme() {
      return new UserInvalidTokenIdRespTupleScheme();
    }
  }

  private static class UserInvalidTokenIdRespTupleScheme extends org.apache.thrift.scheme.TupleScheme<UserInvalidTokenIdResp> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UserInvalidTokenIdResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.response.write(oprot);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTokenIdList()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetTokenIdList()) {
        {
          oprot.writeI32(struct.tokenIdList.size());
          for (java.lang.String _iter30 : struct.tokenIdList)
          {
            oprot.writeString(_iter30);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UserInvalidTokenIdResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.response = new ResponseInfo();
      struct.response.read(iprot);
      struct.setResponseIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list31 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.tokenIdList = new java.util.ArrayList<java.lang.String>(_list31.size);
          java.lang.String _elem32;
          for (int _i33 = 0; _i33 < _list31.size; ++_i33)
          {
            _elem32 = iprot.readString();
            struct.tokenIdList.add(_elem32);
          }
        }
        struct.setTokenIdListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

