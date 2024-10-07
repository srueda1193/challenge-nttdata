DROP TABLE IF EXISTS "public"."account";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."account" (
    "code" uuid NOT NULL,
    "account_number" varchar(255),
    "account_type" varchar(255),
    "balance" float8,
    "client_id" int8,
    "status" bool,
    PRIMARY KEY ("code")
);


DROP TABLE IF EXISTS "public"."transaction";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."transaction" (
    "code" uuid NOT NULL,
    "amount" float8,
    "balance" float8,
    "transaction_date" timestamp,
    "transaction_type" varchar(255),
    "account_code" uuid,
    CONSTRAINT "fktm2jl3b52h20wg72sub731qid" FOREIGN KEY ("account_code") REFERENCES "public"."account"("code"),
    PRIMARY KEY ("code")
);