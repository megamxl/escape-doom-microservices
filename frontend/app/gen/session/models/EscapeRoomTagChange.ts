// version: 1.0

/**
 * @description The tag to add or remove
 */
export type EscapeRoomTagChange = {
  /**
   * @description The tag to add or remove
   * @type string | undefined
   */
  tag_name?: string
  /**
   * @description The id of the escape-room instance
   * @type string | undefined, uuid
   */
  escape_room_session_id?: string
}