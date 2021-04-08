CREATE TYPE "UserRole" AS ENUM (
    'admin',
    'client'
)
;

CREATE TYPE "ClientStatus" AS ENUM (
	'normal',
	'banned'
)
;

CREATE TYPE "Star" AS ENUM(
	'ONE',
    'TWO',
    'THREE',
    'FOUR',
    'FIVE'
)
;

CREATE TYPE "OrderStatus" AS ENUM(
	'ordered',
	'ready',
	'picked_up',
	'canceled',
	'overdue'
)
;

CREATE TYPE "PaymentType" AS ENUM(
	'cash',
	'cashless'
)
;