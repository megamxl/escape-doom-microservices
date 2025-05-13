ALTER TABLE node
ADD node_specifics jsonb;

ALTER TABLE node
DROP imageuri;

ALTER TABLE node
DROP node_type;
