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

INSERT INTO "StudentToSkills" ("Skills", "StudentId")
VALUES ('C', (SELECT "StudentId"
              FROM "Student"
              WHERE "Email" = 'dennis.ritchie@efrei.net')),
       ('Java', (SELECT "StudentId"
                 FROM "Student"
                 WHERE "Email" = 'ada.lovelace@efrei.net')),
       ('CSS', (SELECT "StudentId"
                FROM "Student"
                WHERE "Email" = 'andy.warhol@efrei.net')),
       ('Git', (SELECT "StudentId"
                FROM "Student"
                WHERE "Email" = 'andy.warhol@efrei.net')),
       ('Git', (SELECT "StudentId"
                FROM "Student"
                WHERE "Email" = 'dennis.ritchie@efrei.net'));

INSERT INTO "Company" ("Name", "address")
VALUES ('EFREI', '30-32 avenue de la République 94 800 Villejuif'),
       ('Mairie de Paris', 'Hôtel de Ville de Paris Place de l''Hôtel de Ville 75196 Paris cedex 04 '),
       ('Thalès', '11-13 Avenue Carnot, 91300 Massy, France - MASSY'),
       ('Carrefour', '93 Avenue de Paris 91300 Massy');

INSERT INTO "Internship" ("Description", "WebSurvey", "MidInternInfo", "Begining", "End", "InternSupervisor",
                          "StudentId", "CompanyId")
VALUES ('This is a desc', false, false, to_date('26-04-2021', 'DD-MM-YYYY'), to_date('22-09-2021', 'DD-MM-YYYY'),
        'Patrick', (SELECT "StudentId" FROM "Student" WHERE "Email" = 'dennis.ritchie@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Carrefour')),
       ('This is a desc', true, false, to_date('27-04-2021', 'DD-MM-YYYY'), to_date('22-09-2021', 'DD-MM-YYYY'),
        'Pierrot', (SELECT "StudentId" FROM "Student" WHERE "Email" = 'ada.lovelace@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'EFREI')),
       ('This is a desc', true, true, to_date('27-02-2020', 'DD-MM-YYYY'), to_date('22-010-2020', 'DD-MM-YYYY'),
        'Marie', (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'EFREI')),
       ('This is a desc', false, false, to_date('26-03-2021', 'DD-MM-YYYY'), to_date('22-08-2021', 'DD-MM-YYYY'),
        'Patrick', (SELECT "StudentId" FROM "Student" WHERE "Email" = 'alan.turing@efrei.net'),
        (SELECT "CompanyId" FROM "Company" WHERE "Name" = 'Thalès'));

INSERT INTO "Marks" ("Tech", "Communication", "InternshipId")
VALUES (17, 12, (SELECT "InternshipId"
                 FROM "Internship"
                 WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net')));

INSERT INTO "Comments" ("StudentComm", "SupervisorComm", "InternshipId")
VALUES (true, true, (SELECT "InternshipId"
                     FROM "Internship"
                     WHERE "StudentId" = (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net')));

INSERT INTO "FinalReport" ("Title", "Report", "InternshipId")
VALUES ('My cool report.pdf', true, (SELECT "InternshipId"
                                     FROM "Internship"
                                     WHERE "StudentId" = (SELECT "StudentId"
                                                          FROM "Student"
                                                          WHERE "Email" = 'katie.bouman@efrei.net')));

INSERT INTO "Visit" ("Done", "Planned", "VisitReport", "InternshipId")
VALUES (true, true, true, (SELECT "InternshipId"
                           FROM "Internship"
                           WHERE "StudentId" =
                                 (SELECT "StudentId" FROM "Student" WHERE "Email" = 'katie.bouman@efrei.net')))