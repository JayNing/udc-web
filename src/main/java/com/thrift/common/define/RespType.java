/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.common.define;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum RespType implements org.apache.thrift.TEnum {
  RespType_Unknown(0),
  RespType_Count(1),
  RespType_List(2),
  RespType_RespCode(3),
  RespType_Object(4);

  private final int value;

  private RespType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static RespType findByValue(int value) { 
    switch (value) {
      case 0:
        return RespType_Unknown;
      case 1:
        return RespType_Count;
      case 2:
        return RespType_List;
      case 3:
        return RespType_RespCode;
      case 4:
        return RespType_Object;
      default:
        return null;
    }
  }
}