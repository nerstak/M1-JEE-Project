CREATE TABLE "Tutor" (
  "TutorId" UUID PRIMARY KEY,
  "Name" varchar,
  "Firstname" varchar,
  "Pwd" varchar
);

CREATE TABLE "Student" (
  "StudentId" UUID PRIMARY KEY,
  "Name" varchar,
  "Firstname" varchar,
  "Group" varchar,
  "LinkedInProfile" varchar,
  "TutorId" UUID
);

CREATE TABLE "Skills" (
  "Skill" varchar PRIMARY KEY
);

CREATE TABLE "StudentToSkills" (
  "StudentId" UUID,
  "Skills" varchar,
  PRIMARY KEY ("StudentId", "Skills")
);

CREATE TABLE "Internship" (
  "InternshipId" UUID PRIMARY KEY,
  "Description" text,
  "WebSurvey" boolean,
  "MidInternInfo" boolean,
  "Begining" date,
  "End" date,
  "InternSupervisor" varchar,
  "StudentId" UUID,
  "CompanyId" UUID
);

CREATE TABLE "Company" (
  "CompanyId" UUID PRIMARY KEY,
  "Name" varchar,
  "address" varchar
);

CREATE TABLE "Marks" (
  "MarksId" UUID PRIMARY KEY,
  "Tech" integer,
  "Communication" integer,
  "InternshipId" UUID
);

CREATE TABLE "Comments" (
  "CommentsId" UUID PRIMARY KEY,
  "StudentComm" boolean,
  "SupervisorComm" boolean,
  "InternshipId" UUID
);

CREATE TABLE "FinalReport" (
  "FinalReportId" UUID PRIMARY KEY,
  "Title" varchar,
  "Report" boolean,
  "InternshipId" UUID
);

CREATE TABLE "Visit" (
  "VisitId" UUID PRIMARY KEY,
  "Done" boolean,
  "Planned" boolean,
  "VisitReport" boolean,
  "InternshipId" UUID
);

ALTER TABLE "Student" ADD FOREIGN KEY ("TutorId") REFERENCES "Tutor" ("TutorId");

ALTER TABLE "StudentToSkills" ADD FOREIGN KEY ("StudentId") REFERENCES "Student" ("StudentId");

ALTER TABLE "StudentToSkills" ADD FOREIGN KEY ("Skills") REFERENCES "Skills" ("Skill");

ALTER TABLE "Internship" ADD FOREIGN KEY ("StudentId") REFERENCES "Student" ("StudentId");

ALTER TABLE "Internship" ADD FOREIGN KEY ("CompanyId") REFERENCES "Company" ("CompanyId");

ALTER TABLE "Marks" ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "Comments" ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "FinalReport" ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

ALTER TABLE "Visit" ADD FOREIGN KEY ("InternshipId") REFERENCES "Internship" ("InternshipId");

