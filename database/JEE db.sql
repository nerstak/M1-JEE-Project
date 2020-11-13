-- You need to be connected to st2eedb to execute this part
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "Tutor"
(
    "TutorId"   UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Name"      varchar,
    "Firstname" varchar,
    "Pwd"       varchar,
    "Email"     varchar
);

CREATE TABLE "Student"
(
    "StudentId"       UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Name"            varchar,
    "Firstname"       varchar,
    "Email"           varchar,
    "Group"           varchar,
    "LinkedInProfile" varchar,
    "TutorId"         UUID
);

CREATE TABLE "Skills"
(
    "SkillId" UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Skill" varchar
);

CREATE TABLE "StudentToSkills"
(
    "StudentId" UUID,
    "SkillsId"    UUID,
    PRIMARY KEY ("StudentId", "SkillsId")
);

CREATE TABLE "Keywords"
(
    "KeywordsId"   UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Keywords"     varchar
);

CREATE TABLE "StudentsToKeywords"
(
    "StudentId" UUID,
    "KeywordsId"    UUID,
    PRIMARY KEY ("StudentId", "KeywordsId")
);

CREATE TABLE "Internship"
(
    "InternshipId"     UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Description"      text,
    "WebSurvey"        boolean,
    "MidInternInfo"    boolean,
    "Beginning"        date,
    "End"              date,
    "StudentId"        UUID,
    "CompanyId"        UUID,
    "Cdc"              boolean,
    "CompanyEval"      boolean,
    "Defense"          boolean,
    "InternSupervisor" varchar
);

CREATE TABLE "Company"
(
    "CompanyId" UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Name"      varchar,
    "Address"   varchar
);

CREATE TABLE "Marks"
(
    "MarksId"       UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Tech"          integer,
    "Communication" integer,
    "InternshipId"  UUID
);

CREATE TABLE "Comments"
(
    "CommentsId"     UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "StudentComm"    varchar,
    "SupervisorComm" varchar,
    "InternshipId"   UUID
);

CREATE TABLE "FinalReport"
(
    "FinalReportId" UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Title"         varchar,
    "Report"        boolean,
    "InternshipId"  UUID
);

CREATE TABLE "Visit"
(
    "VisitId"      UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    "Done"         boolean,
    "Planned"      boolean,
    "VisitReport"  boolean,
    "InternshipId" UUID
);


ALTER TABLE "Student"
    ADD FOREIGN KEY ("TutorId") REFERENCES "Tutor" ("TutorId");

ALTER TABLE "StudentToSkills"
    ADD FOREIGN KEY ("StudentId") REFERENCES "Student" ("StudentId");

ALTER TABLE "StudentsToKeywords"
    ADD FOREIGN KEY ("KeywordsId") REFERENCES "Keywords" ("KeywordsId");

ALTER TABLE "StudentsToKeywords"
    ADD FOREIGN KEY ("StudentId") REFERENCES "Student" ("StudentId");

ALTER TABLE "StudentToSkills"
    ADD FOREIGN KEY ("SkillsId") REFERENCES "Skills" ("SkillId");

ALTER TABLE "Internship"
    ADD FOREIGN KEY ("StudentId") REFERENCES "Student" ("StudentId");

ALTER TABLE "Internship"
    ADD FOREIGN KEY ("CompanyId") REFERENCES "Company" ("CompanyId");

ALTER TABLE "Marks"
    ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "Comments"
    ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "FinalReport"
    ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "Visit"
    ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");


CREATE VIEW internships_data AS (
      SELECT S.*,
             I."InternshipId", I."CompanyId", I."Description", I."MidInternInfo", I."WebSurvey", I."Beginning", I."End", I."Cdc", I."CompanyEval", I."Defense", I."InternSupervisor",
             C."Name" AS "CompanyName", C."Address",
             V."Done", V."Planned", V."VisitReport", V."VisitId",
             M."Communication",M."Tech", M."MarksId",
             Fr."FinalReportId", Fr."Title", Fr."Report"


      FROM "Student" S
               LEFT JOIN "Internship" I
                         ON S."StudentId" = I."StudentId"
               JOIN "Company" C
                    ON I."CompanyId" = C."CompanyId"
               LEFT JOIN "Visit" V
                    ON I."InternshipId" = V."InternshipId"
               LEFT JOIN "Marks" M
                    ON I."InternshipId" = M."InternshipId"
               LEFT JOIN "FinalReport" Fr
                    ON Fr."InternshipId" = I."InternshipId"


      ORDER BY (I."Beginning") DESC);

CREATE VIEW internships_data_details AS (
    SELECT S.*, C."CommentsId", C."StudentComm", C."SupervisorComm"
    FROM internships_data S
        LEFT JOIN "Comments" C
            ON S."InternshipId" = C."InternshipId"
                                      );

CREATE VIEW students_skills AS (
   SELECT S.*, sts."StudentId"
   FROM "Skills" S
            JOIN "StudentToSkills" sts
                 ON sts."SkillsId" = s."SkillId"
                               );

GRANT ALL PRIVILEGES ON DATABASE st2eedb TO adm;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to adm;

