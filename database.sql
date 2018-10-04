--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

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
-- Name: chat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat (
    id integer NOT NULL,
    name character varying(30),
    date date
);


ALTER TABLE public.chat OWNER TO postgres;

--
-- Name: chat_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat_user (
    id_user integer NOT NULL,
    id_chat integer NOT NULL
);


ALTER TABLE public.chat_user OWNER TO postgres;

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
-- Name: group_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_authority (
    id integer NOT NULL,
    id_group integer,
    name_authority text
);


ALTER TABLE public.group_authority OWNER TO postgres;

--
-- Name: group_member; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_member (
    id_group integer DEFAULT 0 NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.group_member OWNER TO postgres;

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
-- Name: planet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planet (
    id integer NOT NULL,
    name_planet character varying(15),
    id_system integer,
    id_fraction integer,
    location_planet_x integer,
    location_planet_y integer,
    id_user integer,
    type_weather integer
);


ALTER TABLE public.planet OWNER TO postgres;

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
    name_resources text,
    id_planet integer,
    count integer,
    type integer
);


ALTER TABLE public.resource OWNER TO postgres;

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
    access boolean NOT NULL,
    id_fraction integer NOT NULL,
    id_type integer,
    id_state integer,
    privacy integer
);


ALTER TABLE public.task OWNER TO postgres;

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
-- Name: user_battle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_battle (
    id_user integer NOT NULL,
    id_battle integer NOT NULL
);


ALTER TABLE public.user_battle OWNER TO postgres;

--
-- Name: user_fraction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_fraction (
    id_fraction integer NOT NULL,
    id_user integer NOT NULL,
    date date,
    actual boolean
);


ALTER TABLE public.user_fraction OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    game_name text,
    login text,
    password text,
    level integer,
    id_state integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: vote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vote (
    id integer NOT NULL,
    id_user integer,
    id_result integer
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
-- Data for Name: base; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.base (id, name_base, id_user, id_system, location_base_x, location_base_y) FROM stdin;
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
1	testing	2018-10-03
2	chat 2	2018-10-04
3	test_post	2018-10-03
\.


--
-- Data for Name: chat_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat_user (id_user, id_chat) FROM stdin;
\.


--
-- Data for Name: fraction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fraction (id, name_fraction, id_politics) FROM stdin;
\.


--
-- Data for Name: group_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_authority (id, id_group, name_authority) FROM stdin;
1	1	GROUP_PLAYER
\.


--
-- Data for Name: group_member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_member (id_group, id_user) FROM stdin;
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.groups (id, name) FROM stdin;
1	players
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, id_chat, id_user, message) FROM stdin;
\.


--
-- Data for Name: planet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planet (id, name_planet, id_system, id_fraction, location_planet_x, location_planet_y, id_user, type_weather) FROM stdin;
\.


--
-- Data for Name: politics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.politics (id, name_politics) FROM stdin;
\.


--
-- Data for Name: resource; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.resource (id, name_resources, id_planet, count, type) FROM stdin;
\.


--
-- Data for Name: results; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.results (id, id_voting, name) FROM stdin;
\.


--
-- Data for Name: ship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ship (id, hp, name_ship, id_base, id_system, id_user, location_ship_x, location_ship_y, speed, protection, state) FROM stdin;
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
\.


--
-- Data for Name: state_ship; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_ship (id, name) FROM stdin;
\.


--
-- Data for Name: state_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_task (id, name_state) FROM stdin;
\.


--
-- Data for Name: state_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.state_user (id, name) FROM stdin;
\.


--
-- Data for Name: system; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.system (id, name_system) FROM stdin;
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id, name, description, access, id_fraction, id_type, id_state, privacy) FROM stdin;
\.


--
-- Data for Name: type_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_resources (id, name) FROM stdin;
\.


--
-- Data for Name: type_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_task (id, name_state) FROM stdin;
\.


--
-- Data for Name: type_weather; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_weather (id, name) FROM stdin;
\.


--
-- Data for Name: user_battle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_battle (id_user, id_battle) FROM stdin;
\.


--
-- Data for Name: user_fraction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_fraction (id_fraction, id_user, date, actual) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, game_name, login, password, level, id_state) FROM stdin;
\.


--
-- Data for Name: vote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vote (id, id_user, id_result) FROM stdin;
\.


--
-- Data for Name: voting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.voting (id, id_chat, message) FROM stdin;
\.


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
-- Name: group_member group_member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_member
    ADD CONSTRAINT group_member_pkey PRIMARY KEY (id_group, id_user);


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
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: vote vote_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vote
    ADD CONSTRAINT vote_pkey PRIMARY KEY (id);


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
-- Name: fraction fraction_id_politics_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraction
    ADD CONSTRAINT fraction_id_politics_fkey FOREIGN KEY (id_politics) REFERENCES public.politics(id) ON DELETE SET NULL;


--
-- Name: group_authority group_authority_id_group_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_authority
    ADD CONSTRAINT group_authority_id_group_fkey FOREIGN KEY (id_group) REFERENCES public.groups(id) ON DELETE CASCADE;


--
-- Name: group_member group_member_id_group_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_member
    ADD CONSTRAINT group_member_id_group_fkey FOREIGN KEY (id_group) REFERENCES public.groups(id) ON DELETE SET DEFAULT;


--
-- Name: group_member group_member_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_member
    ADD CONSTRAINT group_member_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: messages messages_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.groups(id) ON DELETE CASCADE;


--
-- Name: planet planet_id_fraction_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planet
    ADD CONSTRAINT planet_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES public.fraction(id) ON DELETE SET NULL;


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
    ADD CONSTRAINT task_privacy_fkey FOREIGN KEY (privacy) REFERENCES public.state_privacy(id);


--
-- Name: user_battle user_battle_id_battle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_id_battle_fkey FOREIGN KEY (id_battle) REFERENCES public.battle(id);


--
-- Name: user_battle user_battle_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_battle
    ADD CONSTRAINT user_battle_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id);


--
-- Name: user_fraction user_fraction_id_fraction_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES public.ship(id) ON DELETE CASCADE;


--
-- Name: user_fraction user_fraction_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_fraction
    ADD CONSTRAINT user_fraction_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: users users_id_state_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_state_fkey FOREIGN KEY (id_state) REFERENCES public.state_user(id);


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

