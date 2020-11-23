INSERT INTO tutor(firstname, name, email, pwd)
VALUES ('Jean', 'Pierre', 'jean.pierre@efrei.net', 'password'),
       ('Lucie', 'Sky', 'lucie.sky@efrei.net', 'password'),
       ('John', 'Lennon', 'john.lennon@efrei.net', 'password'),
       ('Murphy', 'Cooper', 'murphy.cooper@efrei.net', 'password');

INSERT INTO student (firstname, name, email, student_group, linkedin_profile, tutor_id)
VALUES ('Andy', 'Warhol', 'andy.warhol@efrei.net', 'A', 'https://www.linkedin.com/in/andy-wahrol-15',
        (SELECT tutor_id FROM tutor WHERE email = 'jean.pierre@efrei.net')),
       ('Ada', 'Lovelace', 'ada.lovelace@efrei.net', 'B', 'https://www.linkedin.com/in/ada-lovelace-1564',
        (SELECT tutor_id FROM tutor WHERE email = 'lucie.sky@efrei.net')),
       ('Katie', 'Bouman', 'katie.bouman@efrei.net', 'A', 'https://www.linkedin.com/in/katie-bouman-9851',
        (SELECT tutor_id FROM tutor WHERE email = 'jean.pierre@efrei.net')),
       ('Peggy', 'Johnson', 'peggy.johnson@efrei.net', 'C', 'https://www.linkedin.com/in/peggy-johnson-999123',
        (SELECT tutor_id FROM tutor WHERE email = 'jean.pierre@efrei.net')),
       ('Alan', 'Turing', 'alan.turing@efrei.net', 'C', 'https://www.linkedin.com/in/alan-turing-4963',
        (SELECT tutor_id FROM tutor WHERE email = 'lucie.sky@efrei.net')),
       ('Dennis', 'Ritchie', 'dennis.ritchie@efrei.net', 'B', 'https://www.linkedin.com/in/dennis-ritchie-4831',
        (SELECT tutor_id FROM tutor WHERE email = 'john.lennon@efrei.net'));

INSERT INTO skills(skill)
VALUES ('C'),
       ('C#'),
       ('Java'),
       ('C++'),
       ('J2EE'),
       ('Docker'),
       ('Git'),
       ('CSS'),
       ('SQL');

INSERT INTO student_to_skills (skill_id, student_id)
VALUES ((SELECT skill_id FROM skills WHERE skill = 'C'),
        (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'C++'),
        (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'Java'),
        (SELECT student_id FROM student WHERE email = 'ada.lovelace@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'SQL'),
        (SELECT student_id FROM student WHERE email = 'ada.lovelace@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'Java'),
        (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'J2EE'),
        (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'Docker'),
        (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'CSS'),
        (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'C#'),
        (SELECT student_id FROM student WHERE email = 'alan.turing@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'Git'),
        (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net')),
       ((SELECT skill_id FROM skills WHERE skill = 'Git'),
        (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net'));

INSERT INTO keywords(keyword)
VALUES ('Etranger'),
       ('Remunéré'),
       ('Labo'),
       ('Long'),
       ('Cool'),
       ('Bonus');

INSERT INTO company (name, address)
VALUES ('EFREI', '30-32 avenue de la République 94 800 Villejuif'),
       ('Mairie de Paris', 'Hôtel de Ville de Paris Place de l''Hôtel de Ville 75196 Paris cedex 04 '),
       ('Thalès', '11-13 Avenue Carnot, 91300 Massy, France - MASSY'),
       ('Carrefour', '93 Avenue de Paris 91300 Massy'),
       ('Microsoft', '39 Quai du Président Roosevelt, 92130 Issy-les-Moulineaux');

INSERT INTO internship (description, web_survey, mid_intern_info, beginning, ending,
                        student_id, company_id, cdc, company_eval, defense, intern_supervisor)
VALUES ('Marchant Carrefour', false, false, to_date('26-04-2021', 'DD-MM-YYYY'), to_date('22-09-2021', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'Carrefour'), false, true, true, 'John Doe'),
       ('Efrei time', true, true, to_date('27-07-2020', 'DD-MM-YYYY'), to_date('22-12-2020', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'ada.lovelace@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'EFREI'), true, true, true, 'Elon Musk'),
       ('Presidente Efrei', true, true, to_date('27-02-2020', 'DD-MM-YYYY'), to_date('22-10-2020', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'katie.bouman@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'EFREI'), false, false, true, 'Pic Sou'),
       ('Vice Maire', true, true, to_date('15-10-2018', 'DD-MM-YYYY'), to_date('10-02-2019', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'Mairie de Paris'), false, true, false, 'Jack Arta'),
       ('Bill Gates 2', true, true, to_date('08-09-2016', 'DD-MM-YYYY'), to_date('20-12-2016', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'Microsoft'), false, false, false, 'Paul Ogne'),
       ('Ifreurs boss', false, false, to_date('26-03-2021', 'DD-MM-YYYY'), to_date('22-08-2021', 'DD-MM-YYYY'),
        (SELECT student_id FROM student WHERE email = 'alan.turing@efrei.net'),
        (SELECT company_id FROM company WHERE name = 'Thalès'), false, false, true, 'John Doe-Bis');

INSERT INTO internship_to_keywords (keyword_id, internship_id)
VALUES ((SELECT keyword_id FROM keywords WHERE keyword = 'Etranger'),
        (SELECT internship_id FROM internship WHERE description = 'Marchant Carrefour')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Bonus'),
        (SELECT internship_id FROM internship WHERE description = 'Marchant Carrefour')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Bonus'),
        (SELECT internship_id FROM internship WHERE description = 'Efrei time')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Labo'),
        (SELECT internship_id FROM internship WHERE description = 'Efrei time')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Long'),
        (SELECT internship_id FROM internship WHERE description = 'Presidente Efrei')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Cool'),
        (SELECT internship_id FROM internship WHERE description = 'Presidente Efrei')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Labo'),
        (SELECT internship_id FROM internship WHERE description = 'Vice Maire')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Cool'),
        (SELECT internship_id FROM internship WHERE description = 'Vice Maire')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Etranger'),
        (SELECT internship_id FROM internship WHERE description = 'Bill Gates 2')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Remunéré'),
        (SELECT internship_id FROM internship WHERE description = 'Bill Gates 2')),
       ((SELECT keyword_id FROM keywords WHERE keyword = 'Remunéré'),
        (SELECT internship_id FROM internship WHERE description = 'Ifreurs boss'));

INSERT INTO marks (tech, communication, internship_id)
VALUES (17, 12, (SELECT internship_id
                 FROM internship
                 WHERE student_id = (SELECT student_id FROM student WHERE email = 'katie.bouman@efrei.net'))),
       (15, 16, (SELECT internship_id
                 FROM internship
                 WHERE student_id = (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net'))),
       (9, 19, (SELECT internship_id
                FROM internship
                WHERE student_id = (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net'))),
       (null, null, (SELECT internship_id
                FROM internship
                WHERE student_id = (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net'))),
       (null, null, (SELECT internship_id
                FROM internship
                WHERE student_id = (SELECT student_id FROM student WHERE email = 'alan.turing@efrei.net')));

INSERT INTO comments (student_comm, supervisor_comm, internship_id)
VALUES ('Comments', null, (SELECT internship_id
                           FROM internship
                           WHERE student_id = (SELECT student_id FROM student WHERE email = 'katie.bouman@efrei.net'))),
       ('Comments', 'Comments', (SELECT internship_id
                                 FROM internship
                                 WHERE student_id =
                                       (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net'))),
       ('Comments', 'Comments', (SELECT internship_id
                                 FROM internship
                                 WHERE student_id =
                                       (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net'))),
       (null, null, (SELECT internship_id
                                 FROM internship
                                 WHERE student_id =
                                       (SELECT student_id FROM student WHERE email = 'alan.turing@efrei.net'))),
       (null, null, (SELECT internship_id
                                 FROM internship
                                 WHERE student_id =
                                       (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net')));

INSERT INTO final_report (title, report, internship_id)
VALUES ('My cool report.pdf', true, (SELECT internship_id
                                     FROM internship
                                     WHERE student_id = (SELECT student_id
                                                         FROM student
                                                         WHERE email = 'katie.bouman@efrei.net'))),
       ('Internship Report.pdf', true, (SELECT internship_id
                                        FROM internship
                                        WHERE student_id = (SELECT student_id
                                                            FROM student
                                                            WHERE email = 'andy.warhol@efrei.net'))),
       ('FinalReport.docx', true, (SELECT internship_id
                                   FROM internship
                                   WHERE student_id = (SELECT student_id
                                                       FROM student
                                                       WHERE email = 'peggy.johnson@efrei.net'))),
       (null, false, (SELECT internship_id
                                   FROM internship
                                   WHERE student_id = (SELECT student_id
                                                       FROM student
                                                       WHERE email = 'alan.turing@efrei.net'))),
       (null, false, (SELECT internship_id
                                   FROM internship
                                   WHERE student_id = (SELECT student_id
                                                       FROM student
                                                       WHERE email = 'dennis.ritchie@efrei.net')));

INSERT INTO visit (done, planned, visit_report, internship_id)
VALUES (true, true, true, (SELECT internship_id
                           FROM internship
                           WHERE student_id =
                                 (SELECT student_id FROM student WHERE email = 'katie.bouman@efrei.net'))),
       (false, true, false, (SELECT internship_id
                             FROM internship
                             WHERE student_id =
                                   (SELECT student_id FROM student WHERE email = 'ada.lovelace@efrei.net'))),
       (true, true, true, (SELECT internship_id
                           FROM internship
                           WHERE student_id =
                                 (SELECT student_id FROM student WHERE email = 'andy.warhol@efrei.net'))),
       (true, true, true, (SELECT internship_id
                           FROM internship
                           WHERE student_id =
                                 (SELECT student_id FROM student WHERE email = 'peggy.johnson@efrei.net'))),
       (false, false, false, (SELECT internship_id
                           FROM internship
                           WHERE student_id =
                                 (SELECT student_id FROM student WHERE email = 'dennis.ritchie@efrei.net'))),
       (false, false, false, (SELECT internship_id
                           FROM internship
                           WHERE student_id =
                                 (SELECT student_id FROM student WHERE email = 'alan.turing@efrei.net')));