GRANT ALL PRIVILEGES ON todo.* TO 'appuser'@'%';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS priorities (
    priority_id INT AUTO_INCREMENT PRIMARY KEY,
    grade VARCHAR(20) NOT NULL,
    level INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO priorities (grade, level) VALUES
('High', 1),
('Medium', 2),
('Low', 3);

CREATE TABLE IF NOT EXISTS tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    priority_id INT,
    due_date DATE,
    is_completed BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (priority_id) REFERENCES priorities(priority_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO tasks (title, priority_id, due_date, is_completed) VALUES
('Go for a 30-minute walk in the park', 3, DATE_ADD(CURDATE(), INTERVAL 2 DAY), false),
('Cook a new recipe for dinner tonight', 2, DATE_ADD(CURDATE(), INTERVAL 5 DAY), false),
('Organize the books on your bookshelf by color', 1, DATE_ADD(CURDATE(), INTERVAL 1 DAY), true);