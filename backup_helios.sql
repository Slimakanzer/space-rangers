

CREATE TABLE s242552.base (
    id integer NOT NULL,
    name_base text,
    id_user integer,
    id_system integer,
    location_base_x integer,
    location_base_y integer
);


ALTER TABLE s242552.base OWNER TO s242552;

--
-- Name: base_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.base_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.base_id_seq OWNER TO s242552;

--
-- Name: base_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.base_id_seq OWNED BY s242552.base.id;


--
-- Name: battle; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.battle (
    id integer NOT NULL,
    name character varying(20),
    id_system integer,
    date date
);


ALTER TABLE s242552.battle OWNER TO s242552;

--
-- Name: battle_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.battle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.battle_id_seq OWNER TO s242552;

--
-- Name: battle_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.battle_id_seq OWNED BY s242552.battle.id;


--
-- Name: chat; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.chat (
    id integer NOT NULL,
    name character varying(30),
    date date
);


ALTER TABLE s242552.chat OWNER TO s242552;

--
-- Name: chat_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.chat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.chat_id_seq OWNER TO s242552;

--
-- Name: chat_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.chat_id_seq OWNED BY s242552.chat.id;


--
-- Name: chat_user; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.chat_user (
    id_user integer NOT NULL,
    id_chat integer NOT NULL
);


ALTER TABLE s242552.chat_user OWNER TO s242552;

--
-- Name: complain; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.complain (
    id integer NOT NULL,
    id_message integer,
    date date,
    state boolean
);


ALTER TABLE s242552.complain OWNER TO s242552;

--
-- Name: fraction; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.fraction (
    id integer NOT NULL,
    name_fraction character varying(15) NOT NULL,
    id_politics integer NOT NULL
);


ALTER TABLE s242552.fraction OWNER TO s242552;

--
-- Name: fraction_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.fraction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.fraction_id_seq OWNER TO s242552;

--
-- Name: fraction_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.fraction_id_seq OWNED BY s242552.fraction.id;


--
-- Name: group_authority; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.group_authority (
    id integer NOT NULL,
    name text
);


ALTER TABLE s242552.group_authority OWNER TO s242552;

--
-- Name: groups; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.groups (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.groups OWNER TO s242552;

--
-- Name: messages; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.messages (
    id integer NOT NULL,
    id_chat integer,
    id_user integer,
    message text
);


ALTER TABLE s242552.messages OWNER TO s242552;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.messages_id_seq OWNER TO s242552;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.messages_id_seq OWNED BY s242552.messages.id;


--
-- Name: persistent_logins; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.persistent_logins (
    username character varying(100) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE s242552.persistent_logins OWNER TO s242552;

--
-- Name: planet; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.planet (
    id integer NOT NULL,
    name character varying(30),
    id_system integer,
    location_planet_x integer,
    location_planet_y integer,
    id_user integer,
    type_weather integer
);


ALTER TABLE s242552.planet OWNER TO s242552;

--
-- Name: planet_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.planet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.planet_id_seq OWNER TO s242552;

--
-- Name: planet_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.planet_id_seq OWNED BY s242552.planet.id;


--
-- Name: politics; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.politics (
    id integer NOT NULL,
    name_politics text
);


ALTER TABLE s242552.politics OWNER TO s242552;

--
-- Name: resource; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.resource (
    id integer NOT NULL,
    name text,
    id_planet integer,
    count integer,
    type integer
);


ALTER TABLE s242552.resource OWNER TO s242552;

--
-- Name: resource_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.resource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.resource_id_seq OWNER TO s242552;

--
-- Name: resource_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.resource_id_seq OWNED BY s242552.resource.id;


--
-- Name: results; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.results (
    id integer NOT NULL,
    id_voting integer,
    name character varying(15)
);


ALTER TABLE s242552.results OWNER TO s242552;

--
-- Name: results_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.results_id_seq OWNER TO s242552;

--
-- Name: results_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.results_id_seq OWNED BY s242552.results.id;


--
-- Name: ship; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.ship (
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


ALTER TABLE s242552.ship OWNER TO s242552;

--
-- Name: ship_battle; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.ship_battle (
    id_ship integer NOT NULL,
    id_battle integer NOT NULL
);


ALTER TABLE s242552.ship_battle OWNER TO s242552;

--
-- Name: ship_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.ship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.ship_id_seq OWNER TO s242552;

--
-- Name: ship_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.ship_id_seq OWNED BY s242552.ship.id;


--
-- Name: state_privacy; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_privacy (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.state_privacy OWNER TO s242552;

--
-- Name: state_ship; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_ship (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.state_ship OWNER TO s242552;

--
-- Name: state_task; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_task (
    id integer NOT NULL,
    name_state character varying(30)
);


ALTER TABLE s242552.state_task OWNER TO s242552;

--
-- Name: state_user; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_user (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.state_user OWNER TO s242552;

--
-- Name: state_user_battle; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_user_battle (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.state_user_battle OWNER TO s242552;

--
-- Name: state_user_fraction; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.state_user_fraction (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.state_user_fraction OWNER TO s242552;

--
-- Name: system; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.system (
    id integer NOT NULL,
    name_system character varying(15)
);


ALTER TABLE s242552.system OWNER TO s242552;

--
-- Name: task; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.task (
    id integer NOT NULL,
    name character varying(20),
    description text,
    id_fraction integer NOT NULL,
    id_type integer,
    id_state integer,
    id_privacy integer
);


ALTER TABLE s242552.task OWNER TO s242552;

--
-- Name: task_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.task_id_seq OWNER TO s242552;

--
-- Name: task_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.task_id_seq OWNED BY s242552.task.id;


--
-- Name: type_resources; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.type_resources (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.type_resources OWNER TO s242552;

--
-- Name: type_task; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.type_task (
    id integer NOT NULL,
    name_state character varying(30)
);


ALTER TABLE s242552.type_task OWNER TO s242552;

--
-- Name: type_weather; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.type_weather (
    id integer NOT NULL,
    name character varying(30)
);


ALTER TABLE s242552.type_weather OWNER TO s242552;

--
-- Name: user_account; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.user_account (
    id integer NOT NULL,
    login text,
    password text,
    mail text
);


ALTER TABLE s242552.user_account OWNER TO s242552;

--
-- Name: user_account_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.user_account_id_seq OWNER TO s242552;

--
-- Name: user_account_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.user_account_id_seq OWNED BY s242552.user_account.id;


--
-- Name: user_battle; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.user_battle (
    id_user integer NOT NULL,
    id_battle integer NOT NULL,
    date date,
    id_state integer
);


ALTER TABLE s242552.user_battle OWNER TO s242552;

--
-- Name: user_fraction; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.user_fraction (
    id_fraction integer NOT NULL,
    id_user integer NOT NULL,
    date date,
    id_state integer
);


ALTER TABLE s242552.user_fraction OWNER TO s242552;

--
-- Name: user_group; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.user_group (
    id_user integer NOT NULL,
    id_group integer NOT NULL
);


ALTER TABLE s242552.user_group OWNER TO s242552;

--
-- Name: users; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.users (
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


ALTER TABLE s242552.users OWNER TO s242552;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.users_id_seq OWNER TO s242552;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.users_id_seq OWNED BY s242552.users.id;


--
-- Name: vote; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.vote (
    id_user integer NOT NULL,
    id_result integer NOT NULL
);


ALTER TABLE s242552.vote OWNER TO s242552;

--
-- Name: voting; Type: TABLE; Schema: s242552; Owner: s242552
--

CREATE TABLE s242552.voting (
    id integer NOT NULL,
    id_chat integer,
    message text
);


ALTER TABLE s242552.voting OWNER TO s242552;

--
-- Name: voting_id_seq; Type: SEQUENCE; Schema: s242552; Owner: s242552
--

CREATE SEQUENCE s242552.voting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE s242552.voting_id_seq OWNER TO s242552;

--
-- Name: voting_id_seq; Type: SEQUENCE OWNED BY; Schema: s242552; Owner: s242552
--

ALTER SEQUENCE s242552.voting_id_seq OWNED BY s242552.voting.id;


--
-- Name: base id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.base ALTER COLUMN id SET DEFAULT nextval('s242552.base_id_seq'::regclass);


--
-- Name: battle id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.battle ALTER COLUMN id SET DEFAULT nextval('s242552.battle_id_seq'::regclass);


--
-- Name: chat id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.chat ALTER COLUMN id SET DEFAULT nextval('s242552.chat_id_seq'::regclass);


--
-- Name: fraction id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.fraction ALTER COLUMN id SET DEFAULT nextval('s242552.fraction_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.messages ALTER COLUMN id SET DEFAULT nextval('s242552.messages_id_seq'::regclass);


--
-- Name: planet id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.planet ALTER COLUMN id SET DEFAULT nextval('s242552.planet_id_seq'::regclass);


--
-- Name: resource id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.resource ALTER COLUMN id SET DEFAULT nextval('s242552.resource_id_seq'::regclass);


--
-- Name: results id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.results ALTER COLUMN id SET DEFAULT nextval('s242552.results_id_seq'::regclass);


--
-- Name: ship id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship ALTER COLUMN id SET DEFAULT nextval('s242552.ship_id_seq'::regclass);


--
-- Name: task id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task ALTER COLUMN id SET DEFAULT nextval('s242552.task_id_seq'::regclass);


--
-- Name: user_account id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_account ALTER COLUMN id SET DEFAULT nextval('s242552.user_account_id_seq'::regclass);


--
-- Name: voting id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.voting ALTER COLUMN id SET DEFAULT nextval('s242552.voting_id_seq'::regclass);

--
-- Name: base base_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.base
    ADD CONSTRAINT base_pkey PRIMARY KEY (id);


--
-- Name: battle battle_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.battle
    ADD CONSTRAINT battle_pkey PRIMARY KEY (id);


--
-- Name: chat chat_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.chat
    ADD CONSTRAINT chat_pkey PRIMARY KEY (id);


--
-- Name: chat_user chat_user_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.chat_user
    ADD CONSTRAINT chat_user_pkey PRIMARY KEY (id_chat, id_user);


--
-- Name: complain complain_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.complain
    ADD CONSTRAINT complain_pkey PRIMARY KEY (id);


--
-- Name: fraction fraction_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.fraction
    ADD CONSTRAINT fraction_pkey PRIMARY KEY (id);


--
-- Name: group_authority group_authority_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.group_authority
    ADD CONSTRAINT group_authority_pkey PRIMARY KEY (id);


--
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: persistent_logins persistent_logins_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- Name: planet planet_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.planet
    ADD CONSTRAINT planet_pkey PRIMARY KEY (id);


--
-- Name: politics politics_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.politics
    ADD CONSTRAINT politics_pkey PRIMARY KEY (id);


--
-- Name: resource resourc_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.resource
    ADD CONSTRAINT resourc_pkey PRIMARY KEY (id);


--
-- Name: results results_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.results
    ADD CONSTRAINT results_pkey PRIMARY KEY (id);


--
-- Name: ship_battle ship_battle_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship_battle
    ADD CONSTRAINT ship_battle_pkey PRIMARY KEY (id_ship, id_battle);


--
-- Name: ship ship_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_pkey PRIMARY KEY (id);


--
-- Name: state_task state_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_task
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- Name: state_privacy state_privacy_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_privacy
    ADD CONSTRAINT state_privacy_pkey PRIMARY KEY (id);


--
-- Name: state_ship state_ship_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_ship
    ADD CONSTRAINT state_ship_pkey PRIMARY KEY (id);


--
-- Name: state_user_battle state_user_battle_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_user_battle
    ADD CONSTRAINT state_user_battle_pkey PRIMARY KEY (id);


--
-- Name: state_user_fraction state_user_fraction_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_user_fraction
    ADD CONSTRAINT state_user_fraction_pkey PRIMARY KEY (id);


--
-- Name: state_user state_user_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_user
    ADD CONSTRAINT state_user_pkey PRIMARY KEY (id);


--
-- Name: system system_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.system
    ADD CONSTRAINT system_pkey PRIMARY KEY (id);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: type_task type_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.type_task
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: type_resources type_resources_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.type_resources
    ADD CONSTRAINT type_resources_pkey PRIMARY KEY (id);


--
-- Name: user_account user_account_mail_key; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_account
    ADD CONSTRAINT user_account_mail_key UNIQUE (mail);


--
-- Name: user_account user_account_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);


--
-- Name: user_battle user_battle_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_battle
    ADD CONSTRAINT user_battle_pkey PRIMARY KEY (id_user, id_battle);


--
-- Name: user_fraction user_fraction_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_fraction
    ADD CONSTRAINT user_fraction_pkey PRIMARY KEY (id_fraction, id_user);


--
-- Name: user_group user_group_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_group
    ADD CONSTRAINT user_group_pkey PRIMARY KEY (id_user, id_group);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: vote vote_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.vote
    ADD CONSTRAINT vote_pkey PRIMARY KEY (id_user, id_result);


--
-- Name: voting voting_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.voting
    ADD CONSTRAINT voting_pkey PRIMARY KEY (id);


--
-- Name: type_weather weather_type_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.type_weather
    ADD CONSTRAINT weather_type_pkey PRIMARY KEY (id);


--
-- Name: base base_id_system_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.base
    ADD CONSTRAINT base_id_system_fkey FOREIGN KEY (id_system) REFERENCES s242552.system(id) ON DELETE CASCADE;


--
-- Name: base base_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.base
    ADD CONSTRAINT base_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE SET NULL;


--
-- Name: battle battle_id_system_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.battle
    ADD CONSTRAINT battle_id_system_fkey FOREIGN KEY (id_system) REFERENCES s242552.system(id) ON DELETE CASCADE;


--
-- Name: chat_user chat_user_id_chat_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.chat_user
    ADD CONSTRAINT chat_user_id_chat_fkey FOREIGN KEY (id_chat) REFERENCES s242552.chat(id);


--
-- Name: chat_user chat_user_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.chat_user
    ADD CONSTRAINT chat_user_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE CASCADE;


--
-- Name: complain complain_id_message_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.complain
    ADD CONSTRAINT complain_id_message_fkey FOREIGN KEY (id_message) REFERENCES s242552.messages(id);


--
-- Name: fraction fraction_id_politics_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.fraction
    ADD CONSTRAINT fraction_id_politics_fkey FOREIGN KEY (id_politics) REFERENCES s242552.politics(id) ON DELETE SET NULL;


--
-- Name: groups groups_group_group_authority_fk; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.groups
    ADD CONSTRAINT groups_group_group_authority_fk FOREIGN KEY (id) REFERENCES s242552.group_authority(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: messages messages_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.messages
    ADD CONSTRAINT messages_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.groups(id) ON DELETE CASCADE;


--
-- Name: planet planet_id_system_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.planet
    ADD CONSTRAINT planet_id_system_fkey FOREIGN KEY (id_system) REFERENCES s242552.system(id) ON DELETE CASCADE;


--
-- Name: planet planet_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.planet
    ADD CONSTRAINT planet_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE SET NULL;


--
-- Name: planet planet_type_weather_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.planet
    ADD CONSTRAINT planet_type_weather_fkey FOREIGN KEY (type_weather) REFERENCES s242552.type_weather(id);


--
-- Name: resource resourc_id_planet_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.resource
    ADD CONSTRAINT resourc_id_planet_fkey FOREIGN KEY (id_planet) REFERENCES s242552.planet(id) ON DELETE CASCADE;


--
-- Name: resource resource_type_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.resource
    ADD CONSTRAINT resource_type_fkey FOREIGN KEY (type) REFERENCES s242552.type_resources(id);


--
-- Name: results results_id_voting_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.results
    ADD CONSTRAINT results_id_voting_fkey FOREIGN KEY (id_voting) REFERENCES s242552.voting(id) ON DELETE CASCADE;


--
-- Name: ship_battle ship_battle_id_battle_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship_battle
    ADD CONSTRAINT ship_battle_id_battle_fkey FOREIGN KEY (id_battle) REFERENCES s242552.battle(id);


--
-- Name: ship_battle ship_battle_id_ship_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship_battle
    ADD CONSTRAINT ship_battle_id_ship_fkey FOREIGN KEY (id_ship) REFERENCES s242552.ship(id);


--
-- Name: ship ship_id_base_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_id_base_fkey FOREIGN KEY (id_base) REFERENCES s242552.base(id) ON DELETE SET DEFAULT;


--
-- Name: ship ship_id_system_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_id_system_fkey FOREIGN KEY (id_system) REFERENCES s242552.system(id) ON DELETE CASCADE;


--
-- Name: ship ship_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE CASCADE;


--
-- Name: ship ship_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_state_fkey FOREIGN KEY (state) REFERENCES s242552.state_ship(id);


--
-- Name: task task_id_fraction_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task
    ADD CONSTRAINT task_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES s242552.fraction(id) ON DELETE CASCADE;


--
-- Name: task task_id_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task
    ADD CONSTRAINT task_id_state_fkey FOREIGN KEY (id_state) REFERENCES s242552.state_task(id);


--
-- Name: task task_id_type_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task
    ADD CONSTRAINT task_id_type_fkey FOREIGN KEY (id_type) REFERENCES s242552.type_task(id);


--
-- Name: task task_privacy_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.task
    ADD CONSTRAINT task_privacy_fkey FOREIGN KEY (id_privacy) REFERENCES s242552.state_privacy(id);


--
-- Name: user_battle user_battle_id_battle_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_battle
    ADD CONSTRAINT user_battle_id_battle_fkey FOREIGN KEY (id_battle) REFERENCES s242552.battle(id);


--
-- Name: user_battle user_battle_id_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_battle
    ADD CONSTRAINT user_battle_id_state_fkey FOREIGN KEY (id_state) REFERENCES s242552.state_user_battle(id);


--
-- Name: user_battle user_battle_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_battle
    ADD CONSTRAINT user_battle_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id);


--
-- Name: user_fraction user_fraction_id_fraction_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_fraction
    ADD CONSTRAINT user_fraction_id_fraction_fkey FOREIGN KEY (id_fraction) REFERENCES s242552.fraction(id) ON DELETE CASCADE;


--
-- Name: user_fraction user_fraction_id_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_fraction
    ADD CONSTRAINT user_fraction_id_state_fkey FOREIGN KEY (id_state) REFERENCES s242552.state_user_fraction(id);


--
-- Name: user_fraction user_fraction_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_fraction
    ADD CONSTRAINT user_fraction_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE CASCADE;


--
-- Name: user_group user_group_id_group_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_group
    ADD CONSTRAINT user_group_id_group_fkey FOREIGN KEY (id_group) REFERENCES s242552.groups(id);


--
-- Name: user_group user_group_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_group
    ADD CONSTRAINT user_group_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.user_account(id);


--
-- Name: users users_id_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.users
    ADD CONSTRAINT users_id_state_fkey FOREIGN KEY (id_state) REFERENCES s242552.state_user(id);


--
-- Name: users users_id_user_account_fk; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.users
    ADD CONSTRAINT users_id_user_account_fk FOREIGN KEY (id) REFERENCES s242552.user_account(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vote vote_id_result_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.vote
    ADD CONSTRAINT vote_id_result_fkey FOREIGN KEY (id_result) REFERENCES s242552.results(id) ON DELETE CASCADE;


--
-- Name: vote vote_id_user_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.vote
    ADD CONSTRAINT vote_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE CASCADE;


--
-- Name: voting voting_id_chat_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.voting
    ADD CONSTRAINT voting_id_chat_fkey FOREIGN KEY (id_chat) REFERENCES s242552.chat(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

