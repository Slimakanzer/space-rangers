--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: base; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.base (
    id integer NOT NULL,
    name_base text,
    id_user integer,
    id_system integer,
    location_base_x integer,
    location_base_y integer
);


ALTER TABLE public.base OWNER TO postgres;

--
-- Name: base_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.base_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.base_id_seq OWNER TO postgres;

--
-- Name: base_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.base_id_seq OWNED BY public.base.id;


--
-- Name: battle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.battle (
    id integer NOT NULL,
    name character varying(20),
    id_system integer,
    date date
);


ALTER TABLE public.battle OWNER TO postgres;

--
-- Name: battle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.battle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.battle_id_seq OWNER TO postgres;

--
-- Name: battle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.battle_id_seq OWNED BY public.battle.id;


--
-- Name: chat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat (
    id integer NOT NULL,
    name character varying(30),
    date date
);


ALTER TABLE public.chat OWNER TO postgres;

--
-- Name: chat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.chat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chat_id_seq OWNER TO postgres;

--
-- Name: chat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.chat_id_seq OWNED BY public.chat.id;


--
-- Name: chat_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat_user (
    id_user integer NOT NULL,
    id_chat integer NOT NULL
);


ALTER TABLE public.chat_user OWNER TO postgres;

--
-- Name: complain; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complain (
    id integer NOT NULL,
    id_message integer,
    date date,
    state boolean
);


ALTER TABLE public.complain OWNER TO postgres;

--
-- Name: fraction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fraction (
    id integer NOT NULL,
    name_fraction character varying(15) NOT NULL,
    id_politics integer NOT NULL
);


ALTER TABLE public.fraction OWNER TO postgres;

--
-- Name: fraction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fraction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fraction_id_seq OWNER TO postgres;

--
-- Name: fraction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fraction_id_seq OWNED BY public.fraction.id;


--
-- Name: group_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_authority (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.group_authority OWNER TO postgres;

--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.groups (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    id_chat integer,
    id_user integer,
    message text
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_id_seq OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- Name: persistent_logins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persistent_logins (
    username character varying(100) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins OWNER TO postgres;

--
-- Name: planet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planet (
    id integer NOT NULL,
    name character varying(30),
    id_system integer,
    location_planet_x integer,
    location_planet_y integer,
    id_user integer,
    type_weather integer
);


ALTER TABLE public.planet OWNER TO postgres;

--
-- Name: planet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.planet_id_seq OWNER TO postgres;

--
-- Name: planet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planet_id_seq OWNED BY public.planet.id;


--
-- Name: politics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.politics (
    id integer NOT NULL,
    name_politics text
);


ALTER TABLE public.politics OWNER TO postgres;

--
-- Name: resource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resource (
    id integer NOT NULL,
    name text,
    id_planet integer,
    count integer,
    type integer
);


ALTER TABLE public.resource OWNER TO postgres;

--
-- Name: resource_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.resource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resource_id_seq OWNER TO postgres;

--
-- Name: resource_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.resource_id_seq OWNED BY public.resource.id;


--
-- Name: results; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.results (
    id integer NOT NULL,
    id_voting integer,
    name character varying(15)
);


ALTER TABLE public.results OWNER TO postgres;

--
-- Name: results_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.results_id_seq OWNER TO postgres;

--
-- Name: results_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.results_id_seq OWNED BY public.results.id;


--
-- Name: ship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ship (
    id integer NOT NULL,
    hp integer,
    name_ship character varying(14),
    id_base integer DEFAULT 0,
    id_system integer,
    id_user integer,
    location_ship_x integer,
    location_ship_y integer,
    speed integer,
    protection integer,
    state integer,
    CONSTRAINT ship_hp_check CHECK ((hp >= 0)),
    CONSTRAINT ship_protection_check CHECK (((protection >= 0) AND (protection <= 50))),
    CONSTRAINT ship_speed_check CHECK ((((speed >= 400) AND (speed <= 1000)) OR (speed = 0)))
);


ALTER TABLE public.ship OWNER TO postgres;

--
-- Name: ship_battle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ship_battle (
    id_ship integer NOT NULL,
    id_battle integer NOT NULL
);


ALTER TABLE public.ship_battle OWNER TO postgres;

--
-- Name: ship_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ship_id_seq OWNER TO postgres;

--
-- Name: ship_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ship_id_seq OWNED BY public.ship.id;


--
-- Name: state_privacy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_privacy (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.state_privacy OWNER TO postgres;

--
-- Name: state_ship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_ship (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.state_ship OWNER TO postgres;

--
-- Name: state_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_task (
    id integer NOT NULL,
    name_state character varying(30)
);


ALTER TABLE public.state_task OWNER TO postgres;

--
-- Name: state_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_user (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.state_user OWNER TO postgres;

--
-- Name: state_user_battle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_user_battle (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.state_user_battle OWNER TO postgres;

--
-- Name: state_user_fraction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.state_user_fraction (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.state_user_fraction OWNER TO postgres;

--
-- Name: system; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.system (
    id integer NOT NULL,
    name_system character varying(15)
);


ALTER TABLE public.system OWNER TO postgres;

--
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    id integer NOT NULL,
    name character varying(20),
    description text,
    id_fraction integer NOT NULL,
    id_type integer,
    id_state integer,
    id_privacy integer
);


ALTER TABLE public.task OWNER TO postgres;

--
-- Name: task_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_id_seq OWNER TO postgres;

--
-- Name: task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.task_id_seq OWNED BY public.task.id;


--
-- Name: type_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_resources (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.type_resources OWNER TO postgres;

--
-- Name: type_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_task (
    id integer NOT NULL,
    name_state character varying(30)
);


ALTER TABLE public.type_task OWNER TO postgres;

--
-- Name: type_weather; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_weather (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE public.type_weather OWNER TO postgres;

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_account (
    id integer NOT NULL,
    login text,
    password text,
    mail text
);


ALTER TABLE public.user_account OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_account_id_seq OWNER TO postgres;

--
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;


--
-- Name: user_battle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_battle (
    id_user integer NOT NULL,
    id_battle integer NOT NULL,
    date date,
    id_state integer
);


ALTER TABLE public.user_battle OWNER TO postgres;

--
-- Name: user_fraction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_fraction (
    id_fraction integer NOT NULL,
    id_user integer NOT NULL,
    date date,
    id_state integer
);


ALTER TABLE public.user_fraction OWNER TO postgres;

--
-- Name: user_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_group (
    id_user integer NOT NULL,
    id_group integer NOT NULL
);


ALTER TABLE public.user_group OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login text,
    password text,
    level integer,
    id_state integer,
    email character varying(30),
    first_name character varying(30),
    last_name character varying(30),
    description text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: vote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vote (
    id_user integer NOT NULL,
    id_result integer NOT NULL
);


ALTER TABLE public.vote OWNER TO postgres;

--
-- Name: voting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.voting (
    id integer NOT NULL,
    id_chat integer,
    message text
);


ALTER TABLE public.voting OWNER TO postgres;

--
-- Name: voting_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.voting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.voting_id_seq OWNER TO postgres;

--
-- Name: voting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.voting_id_seq OWNED BY public.voting.id;


--
-- Name: base id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.base ALTER COLUMN id SET DEFAULT nextval('public.base_id_seq'::regclass);


--
-- Name: battle id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.battle ALTER COLUMN id SET DEFAULT nextval('public.battle_id_seq'::regclass);


--
-- Name: chat id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat ALTER COLUMN id SET DEFAULT nextval('public.chat_id_seq'::regclass);


--
-- Name: fraction id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraction ALTER COLUMN id SET DEFAULT nextval('public.fraction_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);


--
-- Name: planet id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet ALTER COLUMN id SET DEFAULT nextval('public.planet_id_seq'::regclass);


--
-- Name: resource id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resource ALTER COLUMN id SET DEFAULT nextval('public.resource_id_seq'::regclass);


--
-- Name: results id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.results ALTER COLUMN id SET DEFAULT nextval('public.results_id_seq'::regclass);


--
-- Name: ship id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship ALTER COLUMN id SET DEFAULT nextval('public.ship_id_seq'::regclass);


--
-- Name: task id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task ALTER COLUMN id SET DEFAULT nextval('public.task_id_seq'::regclass);


--
-- Name: user_account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);


--
-- Name: voting id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voting ALTER COLUMN id SET DEFAULT nextval('public.voting_id_seq'::regclass);


--
-- Data for Name: base; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.base (id, name_base, id_user, id_system, location_base_x, location_base_y) FROM stdin;
6	test	1	2	123	1234
\.


--
-- Data for Name: battle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.battle (id, name, id_system, date) FROM stdin;
\.


--
-- Data for Name: chat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat (id, name, date) FROM stdin;
1	The Best	2018-11-03
2	test2	2018-11-03
3	test2	2018-11-03
4	Gays	\N
\.


--
-- Data for Name: chat_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat_user (id_user, id_chat) FROM stdin;
1	1
1	2
1	3
1	4
\.


--
-- Data for Name: complain; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.complain (id, id_message, date, state) FROM stdin;
\.


--
-- Data for Name: fraction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fraction (id, name_fraction, id_politics) FROM stdin;
8	name	1
\.


--
-- Data for Name: group_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_authority (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_LEADER
3	ROLE_ADMIN
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (id, name) FROM stdin;
1	User
2	leader
3	admin
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, id_chat, id_user, message) FROM stdin;
1	1	1	Hello, geys
2	1	1	hey, h=who are you?
3	1	1	Hello ouwww :3
4	1	1	Hello ouwww :3
5	1	1	Hello ouwww :3
6	1	1	hey, h=who are you?
7	1	1	I'm Gleb
8	2	1	Hello
9	1	1	string
\.


--
-- Data for Name: persistent_logins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persistent_logins (username, series, token, last_used) FROM stdin;
glebb	aeDno1eRw7WMCa1vVy7c3Q==	lHkyMtSz19gwNbEzienWPQ==	2018-11-22 18:56:19.1
glebb	ByHCtNIdSQiZEl+B/cFvxw==	olmXlaD1atZw0nDZVtczCA==	2018-11-22 18:57:29.754
test	doB2ogF2q0IjZO2PZxcTOw==	7H4mseaiFWB4SALO2KEi7Q==	2018-11-24 17:16:06.687
test	XTu+WSgv9P0ulFUR9iC1iA==	6DnfbTA9g6lhaLQ0hP0OyA==	2018-11-24 17:55:12.439
test	F2TB0xF9In9qAIJWPGZ+jA==	0osSV6FBUO6QAmTBR44+cA==	2018-11-24 20:33:20.245
\.


--
-- Data for Name: planet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planet (id, name, id_system, location_planet_x, location_planet_y, id_user, type_weather) FROM stdin;
47	test2	1	228	1337	1	\N
\.


--
-- Data for Name: politics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.politics (id, name_politics) FROM stdin;
1	USSR
\.


--
-- Data for Name: resource; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.resource (id, name, id_planet, count, type) FROM stdin;
91	Iron	47	113	\N
\.


--
-- Data for Name: results; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.results (id, id_voting, name) FROM stdin;
2	1	tree
1	1	ironKek
\.


--
-- Data for Name: ship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ship (id, hp, name_ship, id_base, id_system, id_user, location_ship_x, location_ship_y, speed, protection, state) FROM stdin;
9	123	test	6	2	1	123	1234	500	40	\N
10	530	test2	6	2	1	123	1	600	40	\N
11	780	test2	6	1	1	123	1123	700	40	\N
\.


--
-- Data for Name: ship_battle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ship_battle (id_ship, id_battle) FROM stdin;
\.


--
-- Data for Name: state_privacy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_privacy (id, name) FROM stdin;
1	public
2	private
\.


--
-- Data for Name: state_ship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_ship (id, name) FROM stdin;
1	Сломан
2	В боевой готовности
\.


--
-- Data for Name: state_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_task (id, name_state) FROM stdin;
1	created
\.


--
-- Data for Name: state_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_user (id, name) FROM stdin;
\.


--
-- Data for Name: state_user_battle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_user_battle (id, name) FROM stdin;
1	В бою
2	Вышел из боя
\.


--
-- Data for Name: state_user_fraction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_user_fraction (id, name) FROM stdin;
1	leader
3	candidate
\.


--
-- Data for Name: system; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.system (id, name_system) FROM stdin;
1	Test system
2	Test#32412
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id, name, description, id_fraction, id_type, id_state, id_privacy) FROM stdin;
4	Create test	Test descr	8	1	1	1
5	Create test	Test descr	8	1	1	2
6	Test privacy	test privacy	8	1	\N	2
7	Tes	test privacy	8	1	1	1
\.


--
-- Data for Name: type_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_resources (id, name) FROM stdin;
1	Iron
\.


--
-- Data for Name: type_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_task (id, name_state) FROM stdin;
1	test
\.


--
-- Data for Name: type_weather; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_weather (id, name) FROM stdin;
1	Snow
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_account (id, login, password, mail) FROM stdin;
1	test	$2a$10$aJ7a.8gk/sPlyQsl0I5co./MVJo/6dJAivJKKgsCcgtGiqVlnBoAO	\N
10	glebb	$2a$10$jU0rTXRWZPRDFKea92.sp.tvdKc.qMckDhmcGHQkxz5fDkOF1JXe.	\N
12	gleb.larochkin@gmail.com		gleb.larochkin@gmail.com
\.


--
-- Data for Name: user_battle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_battle (id_user, id_battle, date, id_state) FROM stdin;
\.


--
-- Data for Name: user_fraction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_fraction (id_fraction, id_user, date, id_state) FROM stdin;
8	1	2018-11-08	3
8	10	2018-11-08	\N
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_group (id_user, id_group) FROM stdin;
10	1
10	2
1	1
1	3
12	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, password, level, id_state, email, first_name, last_name, description) FROM stdin;
1	Test	\N	1	\N	\N	\N	\N	\N
10	\N	\N	\N	\N	\N	\N	\N	\N
12	\N	\N	1	\N	\N	\N	\N	\N
\.


--
-- Data for Name: vote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vote (id_user, id_result) FROM stdin;
1	1
\.


--
-- Data for Name: voting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.voting (id, id_chat, message) FROM stdin;
1	1	Voting me
2	1	Test voting
3	2	Test voting with chat 2
\.


--
-- Name: base_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.base_id_seq', 7, true);


--
-- Name: battle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.battle_id_seq', 3, true);


--
-- Name: chat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.chat_id_seq', 4, true);


--
-- Name: fraction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fraction_id_seq', 14, true);


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_id_seq', 9, true);


--
-- Name: planet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planet_id_seq', 47, true);


--
-- Name: resource_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.resource_id_seq', 91, true);


--
-- Name: results_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.results_id_seq', 2, true);


--
-- Name: ship_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ship_id_seq', 11, true);


--
-- Name: task_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_id_seq', 7, true);


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_account_id_seq', 12, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 5, true);


--
-- Name: voting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.voting_id_seq', 3, true);


--
-- Name: base base_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.base
    ADD CONSTRAINT base_pkey PRIMARY KEY (id);


--
-- Name: battle battle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.battle
    ADD CONSTRAINT battle_pkey PRIMARY KEY (id);


--
-- Name: chat chat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_pkey PRIMARY KEY (id);


--
-- Name: chat_user chat_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user
    ADD CONSTRAINT chat_user_pkey PRIMARY KEY (id_chat, id_user);


--
-- Name: complain complain_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complain
    ADD CONSTRAINT complain_pkey PRIMARY KEY (id);


--
-- Name: fraction fraction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraction
    ADD CONSTRAINT fraction_pkey PRIMARY KEY (id);


--
-- Name: group_authority group_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_authority
    ADD CONSTRAINT group_authority_pkey PRIMARY KEY (id);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: persistent_logins persistent_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- Name: planet planet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_pkey PRIMARY KEY (id);


--
-- Name: politics politics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.politics
    ADD CONSTRAINT politics_pkey PRIMARY KEY (id);


--
-- Name: resource resourc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resource
    ADD CONSTRAINT resourc_pkey PRIMARY KEY (id);


--
-- Name: results results_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.results
    ADD CONSTRAINT results_pkey PRIMARY KEY (id);


--
-- Name: ship_battle ship_battle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship_battle
    ADD CONSTRAINT ship_battle_pkey PRIMARY KEY (id_ship, id_battle);


--
-- Name: ship ship_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship
    ADD CONSTRAINT ship_pkey PRIMARY KEY (id);


--
-- Name: state_task state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_task
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- Name: state_privacy state_privacy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_privacy
    ADD CONSTRAINT state_privacy_pkey PRIMARY KEY (id);


--
-- Name: state_ship state_ship_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_ship
    ADD CONSTRAINT state_ship_pkey PRIMARY KEY (id);


--
-- Name: state_user_battle state_user_battle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_user_battle
    ADD CONSTRAINT state_user_battle_pkey PRIMARY KEY (id);


--
-- Name: state_user_fraction state_user_fraction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_user_fraction
    ADD CONSTRAINT state_user_fraction_pkey PRIMARY KEY (id);


--
-- Name: state_user state_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.state_user
    ADD CONSTRAINT state_user_pkey PRIMARY KEY (id);


--
-- Name: system system_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.system
    ADD CONSTRAINT system_pkey PRIMARY KEY (id);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: type_task type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_task
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: type_resources type_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_resources
    ADD CONSTRAINT type_resources_pkey PRIMARY KEY (id);


--
-- Name: user_account user_account_mail_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_mail_key UNIQUE (mail);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: user_battle user_battle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_pkey PRIMARY KEY (id_user, id_battle);


--
-- Name: user_fraction user_fraction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_pkey PRIMARY KEY (id_fraction, id_user);


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id_user, id_group);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: vote vote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vote
    ADD CONSTRAINT vote_pkey PRIMARY KEY (id_user, id_result);


--
-- Name: voting voting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voting
    ADD CONSTRAINT voting_pkey PRIMARY KEY (id);


--
-- Name: type_weather weather_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_weather
    ADD CONSTRAINT weather_type_pkey PRIMARY KEY (id);


--
-- Name: base base_id_system_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.base
    ADD CONSTRAINT base_id_system_fkey FOREIGN KEY (id_system) REFERENCES public.system(id) ON DELETE CASCADE;


--
-- Name: base base_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.base
    ADD CONSTRAINT base_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE SET NULL;


--
-- Name: battle battle_id_system_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.battle
    ADD CONSTRAINT battle_id_system_fkey FOREIGN KEY (id_system) REFERENCES public.system(id) ON DELETE CASCADE;


--
-- Name: chat_user chat_user_id_chat_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user
    ADD CONSTRAINT chat_user_id_chat_fkey FOREIGN KEY (id_chat) REFERENCES public.chat(id);


--
-- Name: chat_user chat_user_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat_user
    ADD CONSTRAINT chat_user_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: complain complain_id_message_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complain
    ADD CONSTRAINT complain_id_message_fkey FOREIGN KEY (id_message) REFERENCES public.messages(id);


--
-- Name: fraction fraction_id_politics_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraction
    ADD CONSTRAINT fraction_id_politics_fkey FOREIGN KEY (id_politics) REFERENCES public.politics(id) ON DELETE SET NULL;


--
-- Name: groups groups_group_group_authority_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_group_group_authority_fk FOREIGN KEY (id) REFERENCES public.group_authority(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: messages messages_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.groups(id) ON DELETE CASCADE;


--
-- Name: planet planet_id_system_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_id_system_fkey FOREIGN KEY (id_system) REFERENCES public.system(id) ON DELETE CASCADE;


--
-- Name: planet planet_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE SET NULL;


--
-- Name: planet planet_type_weather_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_type_weather_fkey FOREIGN KEY (type_weather) REFERENCES public.type_weather(id);


--
-- Name: resource resourc_id_planet_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resource
    ADD CONSTRAINT resourc_id_planet_fkey FOREIGN KEY (id_planet) REFERENCES public.planet(id) ON DELETE CASCADE;


--
-- Name: resource resource_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resource
    ADD CONSTRAINT resource_type_fkey FOREIGN KEY (type) REFERENCES public.type_resources(id);


--
-- Name: results results_id_voting_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.results
    ADD CONSTRAINT results_id_voting_fkey FOREIGN KEY (id_voting) REFERENCES public.voting(id) ON DELETE CASCADE;


--
-- Name: ship_battle ship_battle_id_battle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship_battle
    ADD CONSTRAINT ship_battle_id_battle_fkey FOREIGN KEY (id_battle) REFERENCES public.battle(id);


--
-- Name: ship_battle ship_battle_id_ship_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship_battle
    ADD CONSTRAINT ship_battle_id_ship_fkey FOREIGN KEY (id_ship) REFERENCES public.ship(id);


--
-- Name: ship ship_id_base_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship
    ADD CONSTRAINT ship_id_base_fkey FOREIGN KEY (id_base) REFERENCES public.base(id) ON DELETE SET DEFAULT;


--
-- Name: ship ship_id_system_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship
    ADD CONSTRAINT ship_id_system_fkey FOREIGN KEY (id_system) REFERENCES public.system(id) ON DELETE CASCADE;


--
-- Name: ship ship_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship
    ADD CONSTRAINT ship_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: ship ship_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ship
    ADD CONSTRAINT ship_state_fkey FOREIGN KEY (state) REFERENCES public.state_ship(id);


--
-- Name: task task_id_fraction_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES public.fraction(id) ON DELETE CASCADE;


--
-- Name: task task_id_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_id_state_fkey FOREIGN KEY (id_state) REFERENCES public.state_task(id);


--
-- Name: task task_id_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_id_type_fkey FOREIGN KEY (id_type) REFERENCES public.type_task(id);


--
-- Name: task task_privacy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_privacy_fkey FOREIGN KEY (id_privacy) REFERENCES public.state_privacy(id);


--
-- Name: user_battle user_battle_id_battle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_id_battle_fkey FOREIGN KEY (id_battle) REFERENCES public.battle(id);


--
-- Name: user_battle user_battle_id_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_id_state_fkey FOREIGN KEY (id_state) REFERENCES public.state_user_battle(id);


--
-- Name: user_battle user_battle_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id);


--
-- Name: user_fraction user_fraction_id_fraction_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES public.fraction(id) ON DELETE CASCADE;


--
-- Name: user_fraction user_fraction_id_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_id_state_fkey FOREIGN KEY (id_state) REFERENCES public.state_user_fraction(id);


--
-- Name: user_fraction user_fraction_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: user_group user_group_id_group_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_id_group_fkey FOREIGN KEY (id_group) REFERENCES public.groups(id);


--
-- Name: user_group user_group_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT user_group_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.user_account(id);


--
-- Name: users users_id_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_state_fkey FOREIGN KEY (id_state) REFERENCES public.state_user(id);


--
-- Name: users users_id_user_account_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_user_account_fk FOREIGN KEY (id) REFERENCES public.user_account(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vote vote_id_result_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vote
    ADD CONSTRAINT vote_id_result_fkey FOREIGN KEY (id_result) REFERENCES public.results(id) ON DELETE CASCADE;


--
-- Name: vote vote_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vote
    ADD CONSTRAINT vote_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: voting voting_id_chat_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.voting
    ADD CONSTRAINT voting_id_chat_fkey FOREIGN KEY (id_chat) REFERENCES public.chat(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

