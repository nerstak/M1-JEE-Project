INSERT INTO "Tutor"("Firstname", "Name", "Email","Pwd") VALUES
    ('Jean','Pierre','jean.pierre@efrei.net','password'),
    ('Lucie','Sky','lucie.sky@efrei.net','password'),
    ('John','Lennon','john.lennon@efrei.net','password'),
    ('Murphy','Cooper','murphy.cooper@efrei.net','password');

INSERT INTO "Student" ("Firstname","Name","Email","Group","LinkedInProfile","TutorId")
SELECT 'Andy', 'Warhol','andy.warhol@efrei.net','A','https://www.linkedin.com/in/andy-wahrol-15',
       "Tutor"."TutorId" FROM "Tutor" WHERE "Email" = 'jean.pierre@efrei.net';
INSERT INTO "Student" ("Firstname","Name","Email","Group","LinkedInProfile","TutorId")
SELECT 'Ada', 'Lovelace','ada.lovelace@efrei.net','B','https://www.linkedin.com/in/ada-lovelace-1564',
       "Tutor"."TutorId" FROM "Tutor" WHERE "Email" = 'lucie.sky@efrei.net';
INSERT INTO "Student" ("Firstname","Name","Email","Group","LinkedInProfile","TutorId")
SELECT 'Katie', 'Bouman','katie.bouman@efrei.net','A','https://www.linkedin.com/in/katie-bouman-9851',
       "Tutor"."TutorId" FROM "Tutor" WHERE "Email" = 'jean.pierre@efrei.net';