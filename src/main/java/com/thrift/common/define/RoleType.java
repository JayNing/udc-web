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

public enum RoleType implements org.apache.thrift.TEnum {
  RoleType_Common(1),
  RoleType_Open(2),
  RoleType_Enterprise(3),
  RoleType_Government(4),
  RoleType_Committee(5),
  RoleType_TaxOfficial(6);

  private final int value;

  private RoleType(int value) {
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
  public static RoleType findByValue(int value) { 
    switch (value) {
      case 1:
        return RoleType_Common;
      case 2:
        return RoleType_Open;
      case 3:
        return RoleType_Enterprise;
      case 4:
        return RoleType_Government;
      case 5:
        return RoleType_Committee;
      case 6:
        return RoleType_TaxOfficial;
      default:
        return null;
    }
  }
}