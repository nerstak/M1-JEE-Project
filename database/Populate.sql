INSERT INTO "Tutor"("Firstname", "Name", "Email", "Pwd")
VALUES ('Jean', 'Pierre', 'jean.pierre@efrei.net', 'password'),
       ('Lucie', 'Sky', 'lucie.sky@efrei.net', 'password'),
       ('John', 'Lennon', 'john.lennon@efrei.net', 'password'),
       ('Murphy', 'Cooper', 'murphy.cooper@efrei.net', 'password');

INSERT INTO "Student" ("Firstname", "Name", "Email", "Group", "LinkedInProfile", "TutorId")
VALUES ('Andy', 'Warhol', 'andy.warhol@efrei.net', 'A', 'https://www.linkedin.com/in/andy-wahrol-15',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'jean.pierre@efrei.net')),
       ('Ada', 'Lovelace', 'ada.lovelace@efrei.net', 'B', 'https://www.linkedin.com/in/ada-lovelace-1564',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'lucie.sky@efrei.net')),
       ('Katie', 'Bouman', 'katie.bouman@efrei.net', 'A', 'https://www.linkedin.com/in/katie-bouman-9851',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'jean.pierre@efrei.net')),
       ('Peggy', 'Johnson', 'peggy.johnson@efrei.net', 'C', 'https://www.linkedin.com/in/peggy-johnson-999123',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'jean.pierre@efrei.net')),
       ('Alan', 'Turing', 'alan.turing@efrei.net', 'C', 'https://www.linkedin.com/in/alan-turing-4963',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'lucie.sky@efrei.net')),
       ('Dennis', 'Ritchie', 'dennis.ritchie@efrei.net', 'B', 'https://www.linkedin.com/in/dennis-ritchie-4831',
        (SELECT "TutorId" FROM "Tutor" WHERE "Email" = 'john.lennon@efrei.net'));

INSERT INTO "Skills"("Skill")
VALUES ('C'),
       ('C#'),
       ('Java'),
       ('C++'),
       ('J2EE'),
       ('Docker'),
       ('Git'),
       ('CSS'),
       ('SQL');

INSERT INTO "StudentToSkills" ("SkillsId", "StudentId")
VALUES ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'C'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'C++'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'Java'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'SQL'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'Java'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'J2EE'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'Docker'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'CSS'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'C#'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'alan.turing@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'Git'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net')),
       ((SELECT "SkillId" FROM "Skills" WHERE "Skill" = 'Git'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net'));

INSERT INTO "Keywords"("Keywords")
VALUES ('Test'),
       ('Happy'),
       ('HappyTest'),
       ('Key'),
       ('Word'),
       ('Keyword');

INSERT INTO "StudentsToKeywords" ("KeywordsId", "StudentId")
VALUES ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Test'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Happy'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'HappyTest'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Key'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Word'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Keyword'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Keyword'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Word'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Key'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'alan.turing@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'HappyTest'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net')),
       ((SELECT "KeywordsId" FROM "Keywords" WHERE "Keywords" = 'Happy'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net'));

INSERT INTO "Company" ("Name", "Address")
VALUES ('EFREI', '30-32 avenue de la République 94 800 Villejuif'),
       ('Mairie de Paris', 'Hôtel de Ville de Paris Place de l''Hôtel de Ville 75196 Paris cedex 04 '),
       ('Thalès', '11-13 Avenue Carnot, 91300 Massy, France - MASSY'),
       ('Carrefour', '93 Avenue de Paris 91300 Massy'),
       ('Microsoft', '39 Quai du Président Roosevelt, 92130 Issy-les-Moulineaux');

INSERT INTO "Internship" ("Description", "WebSurvey", "MidInternInfo", "Begining", "End",
                          "StudentId", "CompanyId", "Cdc", "CompanyEval", "Defense", "InternSupervisor")
VALUES ('This is a desc', false, false, to_date('26-04-2021', 'DD-MM-YYYY'), to_date('22-09-2021', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Carrefour'), false, true, true, 'John Doe'),
       ('This is a desc', true, true, to_date('27-07-2020', 'DD-MM-YYYY'), to_date('22-12-2020', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'EFREI'), true, true, true, 'Elon Musk'),
       ('This is a desc', true, true, to_date('27-02-2020', 'DD-MM-YYYY'), to_date('22-10-2020', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'EFREI'), false, false, true, 'Pic Sou'),
       ('This is a desc', true, true, to_date('15-10-2018', 'DD-MM-YYYY'), to_date('10-02-2019', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Mairie de Paris'), false, true, false, 'Jack Arta'),
       ('This is a desc', true, true, to_date('08-09-2016', 'DD-MM-YYYY'), to_date('20-12-2016', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Microsoft'), false, false, false, 'Paul Ogne'),
       ('This is a desc', false, false, to_date('26-03-2021', 'DD-MM-YYYY'), to_date('22-08-2021', 'DD-MM-YYYY'),
        (SELECT "StudentId" FROM "Student" WHERE "Email" = 'alan.turing@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Thalès'), false, false, true, 'John Doe-Bis');

INSERT INTO "Marks" ("Tech", "Communication", "InternshipId")
VALUES (17, 12, (SELECT "InternshipId"
                 FROM "Internship"
                 WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net'))),
       (15, 16, (SELECT "InternshipId"
                 FROM "Internship"
                 WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net'))),
       (9, 19, (SELECT "InternshipId"
                FROM "Internship"
                WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net')));

INSERT INTO "Comments" ("StudentComm", "SupervisorComm", "InternshipId")
VALUES ('Comments', null, (SELECT "InternshipId"
                     FROM "Internship"
                     WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net'))),
       ('Comments', 'Comments', (SELECT "InternshipId"
                     FROM "Internship"
                     WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net'))),
       ('Comments', 'Comments', (SELECT "InternshipId"
                     FROM "Internship"
                     WHERE "StudentId" =
                           (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')));

INSERT INTO "FinalReport" ("Title", "Report", "InternshipId")
VALUES ('My cool report.pdf', true, (SELECT "InternshipId"
                                     FROM "Internship"
                                     WHERE "StudentId" = (SELECT "StudentId"
                                                          FROM "Student"
                                                          WHERE "Email" = 'katie.bouman@efrei.net'))),
       ('Internship Report.pdf', true, (SELECT "InternshipId"
                                        FROM "Internship"
                                        WHERE "StudentId" = (SELECT "StudentId"
                                                             FROM "Student"
                                                             WHERE "Email" = 'andy.warhol@efrei.net'))),
       ('FinalReport.docx', true, (SELECT "InternshipId"
                                   FROM "Internship"
                                   WHERE "StudentId" = (SELECT "StudentId"
                                                        FROM "Student"
                                                        WHERE "Email" = 'peggy.johnson@efrei.net')));

INSERT INTO "Visit" ("Done", "Planned", "VisitReport", "InternshipId")
VALUES (true, true, true, (SELECT "InternshipId"
                           FROM "Internship"
                           WHERE "StudentId" =
                                 (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net'))),
       (false, true, false, (SELECT "InternshipId"
                             FROM "Internship"
                             WHERE "StudentId" =
                                   (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net'))),
       (true, true, true, (SELECT "InternshipId"
                           FROM "Internship"
                           WHERE "StudentId" =
                                 (SELECT "StudentId" FROM "Student" WHERE "Email" = 'andy.warhol@efrei.net'))),
       (true, true, true, (SELECT "InternshipId"
                           FROM "Internship"
                           WHERE "StudentId" =
                                 (SELECT "StudentId" FROM "Student" WHERE "Email" = 'peggy.johnson@efrei.net')));