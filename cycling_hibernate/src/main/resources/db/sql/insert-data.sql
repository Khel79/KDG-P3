INSERT INTO team(id, name, uci_code, founded) VALUES (1, 'Lotto–Soudal', 'LTS', 1985);
INSERT INTO team(id, name, uci_code, founded) VALUES (2, 'Etixx–Quick-Step', 'EQS', 2003);


INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (1, 'Thomas De Gendt', '1986-11-06', 1);
INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (2, 'Jelle Vanendert', '1985-02-19', 1);
INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (3, 'Tim Wellens', '1991-05-10', 1);
INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (4, 'Marcel Kittel', '1988-05-11', 2);
INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (5, 'Tony Martin', '1985-04-23', 2);
INSERT INTO rider(id, name, date_of_birth, team_id) VALUES (6, 'Zdeněk Štybar', '1985-12-11', 2);

INSERT INTO tour(id, `year`) VALUES(1, 2015);
INSERT INTO tour(id, `year`) VALUES(2, 2016);

INSERT INTO stage(id, `sequence`, tour_id) VALUES(1, 1, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(2, 2, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(3, 3, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(4, 4, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(5, 5, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(6, 6, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(7, 7, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(8, 8, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(9, 9, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(10, 10, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(11, 11, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(12, 12, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(13, 13, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(14, 14, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(15, 15, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(16, 16, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(17, 17, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(18, 18, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(19, 19, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(20, 20, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(21, 21, 1);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(22, 1, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(23, 2, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(24, 3, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(25, 4, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(26, 5, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(27, 6, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(28, 7, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(29, 8, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(30, 9, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(31, 10, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(32, 11, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(33, 12, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(34, 13, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(35, 14, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(36, 15, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(37, 16, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(38, 17, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(39, 18, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(40, 19, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(41, 20, 2);
INSERT INTO stage(id, `sequence`, tour_id) VALUES(42, 21, 2);

/* Only the victories are inserted for now... */
INSERT INTO stage_result(id, finish_place, stage_id, rider_id) VALUES(1, 1, 25, 4);
INSERT INTO stage_result(id, finish_place, stage_id, rider_id) VALUES(2, 1, 4, 5);
INSERT INTO stage_result(id, finish_place, stage_id, rider_id) VALUES(3, 1, 6, 6);
INSERT INTO stage_result(id, finish_place, stage_id, rider_id) VALUES(4, 1, 33, 1);
