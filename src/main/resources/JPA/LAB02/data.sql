INSERT INTO users (id,first_name, last_name, birthdate, email) VALUES
(1, 'Maciej', 'Kolanko', '2002-11-08', 'jack@email.com'),
(2, 'Tomasz', 'Strasberg', '2004-11-08', 'tomasz@email.com');

INSERT INTO health_metrics (id, user_id, date, weight, height, heart_rate) VALUES
(1, 1, '2025-11-08', 68.43, 184.00, 123),
(2, 1, '2025-11-07', 68.43, 184.00, 180),
(3, 2, '2025-11-08', 41.43, 172.00, 160);

INSERT INTO statistics (id, user_id, total_trainings, total_distance, total_calories_burned) VALUES
(1, 1, 2, 83.21, 800),
(2, 2, 1, 11.53, 214)