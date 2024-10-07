DROP TABLE IF EXISTS "public"."person_entity";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."person_entity" (
    "code" int8 NOT NULL,
    "address" varchar(255),
    "age" int4,
    "gender" varchar(255),
    "identification" varchar(255),
    "name" varchar(255),
    "phone" varchar(255),
    PRIMARY KEY ("code")
);

DROP TABLE IF EXISTS "public"."client";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."client" (
    "password" varchar(255),
    "status" bool,
    "client_code" int8 NOT NULL,
    CONSTRAINT "fk6wlgyiicrq1858f09ivpip3yr" FOREIGN KEY ("client_code") REFERENCES "public"."person_entity"("code"),
    PRIMARY KEY ("client_code")
);

