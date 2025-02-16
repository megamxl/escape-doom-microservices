// version: 0.0.1

export const nodeTypeEnum = {
  ZOOM: 'ZOOM',
  CONSOLE: 'CONSOLE',
  DETAIL: 'DETAIL',
  STORY: 'STORY',
} as const

export type NodeTypeEnum = (typeof nodeTypeEnum)[keyof typeof nodeTypeEnum]

/**
 * @description The type of a node
 */
export type NodeType = NodeTypeEnum