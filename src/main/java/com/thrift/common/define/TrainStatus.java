/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.thrift.common.define;


import org.apache.thrift.TEnum;

public enum TrainStatus implements TEnum {
  Review(1),
  Pass(2),
  Finish(3),
  Refuse(4);

  private final int value;

  private TrainStatus(int value) {
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
  public static TrainStatus findByValue(int value) {
    switch (value) {
      case 1:
        return Review;
      case 2:
        return Pass;
      case 3:
        return Finish;
      case 4:
        return Refuse;
      default:
        return null;
    }
  }
}
