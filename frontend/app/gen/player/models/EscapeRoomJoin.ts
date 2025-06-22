// version: 1.0

/**
 * @description The escape-room instance to join
 */
export type EscapeRoomJoin = {
  /**
   * @description The room-pin to join the escape-room
   * @minLength 100000
   * @maxLength 999999
   * @type number | undefined
   */
  room_pin?: number
  /**
   * @description The name of the player
   * @minLength 1
   * @maxLength 128
   * @type string | undefined
   */
  player_name?: string
}