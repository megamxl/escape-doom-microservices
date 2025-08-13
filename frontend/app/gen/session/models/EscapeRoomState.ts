// version: 1.0

export const escapeRoomStateEnum = {
  open: 'open',
  closed: 'closed',
  started: 'started',
  finished: 'finished',
} as const

export type EscapeRoomStateEnum = (typeof escapeRoomStateEnum)[keyof typeof escapeRoomStateEnum]

/**
 * @description The state of an escape-room instance
 */
export type EscapeRoomState = EscapeRoomStateEnum