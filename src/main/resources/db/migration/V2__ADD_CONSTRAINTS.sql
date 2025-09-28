
-- Add unique constraint on email
ALTER TABLE users ADD CONSTRAINT UK_users_email UNIQUE (email);

-- Add check constraint for name length
ALTER TABLE users ADD CONSTRAINT CK_users_name_length
    CHECK (CHAR_LENGTH(TRIM(name)) >= 2);