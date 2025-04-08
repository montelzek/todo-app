-- Tworzenie tabeli priorities
CREATE TABLE IF NOT EXISTS priorities (
                                          priority_id INT AUTO_INCREMENT PRIMARY KEY,
                                          grade VARCHAR(20) NOT NULL,
    level INT NOT NULL
    );

-- Wstawianie 3 priorytetów
INSERT INTO priorities (grade, level) VALUES
                                          ('High', 1),
                                          ('Medium', 2),
                                          ('Low', 3);

-- Tworzenie tabeli tasks z kluczem obcym
CREATE TABLE IF NOT EXISTS tasks (
                                     task_id INT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(200) NOT NULL,
    priority_id INT,
    due_date DATE,
    is_completed BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (priority_id) REFERENCES priorities(priority_id)
    );

-- Wstawianie zadań z JAWNYM przypisaniem priority_id
INSERT INTO tasks (title, priority_id, due_date, is_completed) VALUES
                                                                   ('Zrobić zakupy', 3, DATE_ADD(CURDATE(), INTERVAL 2 DAY), false),
                                                                   ('Napisać raport', 2, DATE_ADD(CURDATE(), INTERVAL 5 DAY), false),
                                                                   ('Spotkanie z zespołem', 1, DATE_ADD(CURDATE(), INTERVAL 1 DAY), true);