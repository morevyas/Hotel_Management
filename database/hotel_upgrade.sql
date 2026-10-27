-- Run after selecting/creating hotelmanagementsystem1.
USE hotelmanagementsystem1;
CREATE TABLE IF NOT EXISTS reservations (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, guest_name VARCHAR(120) NOT NULL, phone VARCHAR(30), room_number VARCHAR(20),
 check_in DATE NOT NULL, check_out DATE NOT NULL, guests INT NOT NULL DEFAULT 1,
 status VARCHAR(20) NOT NULL DEFAULT 'BOOKED', total_amount DECIMAL(12,2) NOT NULL DEFAULT 0,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, INDEX idx_res_dates(check_in,check_out), INDEX idx_res_status(status)
);
CREATE TABLE IF NOT EXISTS invoices (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, invoice_number VARCHAR(40) NOT NULL UNIQUE, guest_name VARCHAR(120) NOT NULL,
 room_number VARCHAR(20), amount DECIMAL(12,2) NOT NULL DEFAULT 0, paid_amount DECIMAL(12,2) NOT NULL DEFAULT 0,
 payment_status VARCHAR(20) NOT NULL DEFAULT 'UNPAID', created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS housekeeping_tasks (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, room_number VARCHAR(20) NOT NULL, task VARCHAR(255) NOT NULL,
 assigned_to VARCHAR(120), priority VARCHAR(20) NOT NULL DEFAULT 'NORMAL', status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS service_requests (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, guest_name VARCHAR(120) NOT NULL, room_number VARCHAR(20),
 service_type VARCHAR(80) NOT NULL, details TEXT, status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create a dedicated application user rather than using root in a real deployment.
-- Example (set a strong password before running):
-- CREATE USER 'hotel_app'@'localhost' IDENTIFIED BY 'CHANGE_THIS_PASSWORD';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON hotelmanagementsystem1.* TO 'hotel_app'@'localhost';
