-- ============================================================================
-- V5__INSERT_DATA.sql
-- Description: Insert initial seed data for development and testing
-- ============================================================================

-- Insert sample users with USER role
INSERT INTO users (name, lastname, lastmattern, email, dni, age, phonenumber, provider, enabled, role_id, created_at) VALUES
                                                                                      ('David', 'Galvez', 'Choque','david.galvez@example.com','41258563','15','956321456', 'LOCAL', TRUE, 1, NOW()),
                                                                                      ('Rocio', 'Chavez','Ampuero', 'rocio.chavez@example.com','52358563', '11', '965821456','LOCAL', TRUE, 1, NOW()),
                                                                                      ('Pedro', 'Acosta','Carreno', 'pedro.acosta@example.com', '85658563','9','921214562','LOCAL', TRUE, 1, NOW()),
                                                                                      ('Marisol', 'Ugarte','Uchiza', 'marisol.ugarte@example.com', '96358563','25','999214565','LOCAL', TRUE, 1, NOW()),
                                                                                      ('Juan', 'Diaz','Silva', 'juan.diaz@example.com','63258563','32','932621456', 'LOCAL', TRUE, 1, NOW());

-- Insert admin user with hashed password (admin123)
INSERT INTO users (name, lastname, lastmattern, email, dni, age, phonenumber, password, provider, enabled, role_id, created_at)
VALUES (
           'Admin',
           'User',
           'User',
           'admin@hexagonal-demo.com',
           '04025635',
           28,
           '958452325',
           '$2a$12$gBpsIP1vjx4scbpkKgh8w.LA2n0zOie4S86mSJ6D/ByjKdAInZOG2',
           'LOCAL',
           TRUE,
           2,
           CURRENT_TIMESTAMP
       );