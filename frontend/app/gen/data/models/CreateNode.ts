// version: 0.0.1
import type { NodeCreationRequest } from './NodeCreationRequest.ts'
import type { NodeDTO } from './NodeDTO.ts'

/**
 * @description Node created successfully
 */
export type CreateNode201 = NodeDTO

/**
 * @description Lectors ID + Name and Description for a Node
 */
export type CreateNodeMutationRequest = NodeCreationRequest

export type CreateNodeMutationResponse = CreateNode201

export type CreateNodeMutation = {
  Response: CreateNode201
  Request: CreateNodeMutationRequest
  Errors: any
}