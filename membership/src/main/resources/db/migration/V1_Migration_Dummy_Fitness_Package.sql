-- Dummy data for fitness_packages
INSERT INTO fitness_packages (id, name, price, duration)
VALUES
    (1, 'Basic Package', 50000, 30),
    (2, 'Premium Package', 100000, 60),
    (3, 'Ultimate Package', 150000, 90);

-- Dummy data for fitness_package_details
INSERT INTO fitness_package_details (id, fitness_package_id, name, description, time)
VALUES
    (1, 1, 'Cardio Workout', 'Cardio exercises for stamina', 45),
    (2, 1, 'Strength Training', 'Weightlifting for muscle building', 30),
    (3, 2, 'Yoga Session', 'Relaxing yoga poses', 60),
    (4, 3, 'HIIT Session', 'High-Intensity Interval Training', 45);