-- USERS TABLE
CREATE TABLE users (
    user_id int PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(20) CHECK (role IN ('ADMIN', 'OWNER'))
);
ALTER TABLE users
ALTER COLUMN user_id DROP DEFAULT;
ALTER TABLE users
ALTER COLUMN user_id TYPE INT;


-- SITES TABLE
CREATE TABLE sites (
    site_id SERIAL PRIMARY KEY,
    site_type VARCHAR(30),
    length INT,
    width INT,
    status VARCHAR(20),  
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

-- MAINTENANCE TABLE
CREATE TABLE maintenance (
    maintenance_id SERIAL PRIMARY KEY,
    site_id INT,
    amount INT,
    paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (site_id) REFERENCES sites(site_id)
);
--RRQUEST TABLE
CREATE TABLE owner_site_update_requests (
    request_id SERIAL PRIMARY KEY,
    site_id INT,
    new_status VARCHAR(20),
    approval_status VARCHAR(20) DEFAULT 'PENDING'
);






ALTER TABLE maintenance
ADD COLUMN owner_id INT;
ALTER TABLE maintenance
ADD CONSTRAINT maintenance_owner_id_fkey
FOREIGN KEY (owner_id)
REFERENCES users(user_id);







TRUNCATE TABLE sites RESTART IDENTITY CASCADE;
ALTER TABLE sites
ALTER COLUMN site_type DROP NOT NULL;




TRUNCATE TABLE
    maintenance,
    owner_site_update_requests,
    sites,
    users
CASCADE;


INSERT INTO sites (length, width)
SELECT 40, 60
FROM generate_series(1, 10);
INSERT INTO sites (length, width)
SELECT 30, 50
FROM generate_series(1, 10);
INSERT INTO sites (length, width)
SELECT 30, 40
FROM generate_series(1, 15);
ALTER TABLE sites
ALTER COLUMN status SET DEFAULT 'UNOCCUPIED';


