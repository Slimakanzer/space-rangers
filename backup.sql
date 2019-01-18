
CREATE TABLE s242552.base (
    id integer NOT NULL,
    name_base text,
    id_user integer,
    id_system integer DEFAULT 1,
    location_base_x integer,
    location_base_y integer,
    location_base_z integer
);



CREATE SEQUENCE s242552.base_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.battle (
    id integer NOT NULL,
    name character varying(20),
    id_system integer,
    date date
);

CREATE SEQUENCE s242552.battle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE s242552.chat (
    id integer NOT NULL,
    name character varying(30),
    date date
);


CREATE SEQUENCE s242552.chat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE s242552.chat_user (
    id_user integer NOT NULL,
    id_chat integer NOT NULL
);



CREATE TABLE s242552.complain (
    id integer NOT NULL,
    id_message integer,
    date date,
    state character varying(50)
);


CREATE SEQUENCE s242552.complain_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE s242552.fraction (
    id integer NOT NULL,
    name_fraction character varying(15) NOT NULL,
    id_politics integer NOT NULL,
    count_player integer DEFAULT 0
);


CREATE SEQUENCE s242552.fraction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE s242552.group_authority (
    id integer NOT NULL,
    name text
);


CREATE TABLE s242552.groups (
    id integer NOT NULL,
    name character varying(30)
);


CREATE TABLE s242552.messages (
    id integer NOT NULL,
    id_chat integer,
    id_user integer,
    message text
);



CREATE SEQUENCE s242552.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.news (
    id integer NOT NULL,
    name text,
    full_name text,
    date timestamp without time zone
);


CREATE SEQUENCE s242552.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE s242552.persistent_logins (
    username character varying(100) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);



CREATE TABLE s242552.planet (
    id integer NOT NULL,
    name character varying(30),
    id_system integer,
    location_planet_x integer,
    location_planet_y integer,
    id_user integer,
    type_weather integer,
    location_planet_z integer,
    rplanet integer,
    img character varying(50)
);



CREATE SEQUENCE s242552.planet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.politics (
    id integer NOT NULL,
    name_politics text
);


CREATE TABLE s242552.resource (
    id integer NOT NULL,
    name text,
    id_planet integer,
    count integer,
    type integer
);



CREATE SEQUENCE s242552.resource_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.results (
    id integer NOT NULL,
    id_voting integer,
    name character varying(15)
);


CREATE SEQUENCE s242552.results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


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
    state integer DEFAULT 2 NOT NULL,
    type_ship character varying(50),
    location_ship_z integer,
    rotation_ship_x real DEFAULT 0 NOT NULL,
    rotation_ship_y real DEFAULT 0 NOT NULL,
    rotation_ship_z real DEFAULT 0 NOT NULL,
    CONSTRAINT ship_hp_check CHECK (((hp <= 2500) AND (hp >= 0))),
    CONSTRAINT ship_protection_check CHECK (((protection >= 0) AND (protection <= 50))),
    CONSTRAINT ship_speed_check CHECK ((((speed >= 400) AND (speed <= 1000)) OR (speed = 0)))
);


CREATE TABLE s242552.ship_battle (
    id_ship integer NOT NULL,
    id_battle integer NOT NULL
);


CREATE SEQUENCE s242552.ship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE s242552.state_complain (
    name character varying(50) NOT NULL
);


CREATE TABLE s242552.state_privacy (
    id integer NOT NULL,
    name character varying(30)
);



CREATE TABLE s242552.state_ship (
    id integer NOT NULL,
    name character varying(30),
    name_normal character varying(30)
);



CREATE TABLE s242552.state_task (
    id integer NOT NULL,
    name_state character varying(30)
);



CREATE TABLE s242552.state_user (
    id integer NOT NULL,
    name character varying(30)
);



CREATE TABLE s242552.state_user_battle (
    id integer NOT NULL,
    name character varying(30)
);



CREATE TABLE s242552.state_user_fraction (
    id integer NOT NULL,
    name character varying(30),
    name_normal character varying(30)
);



CREATE TABLE s242552.system (
    id integer NOT NULL,
    name_system character varying(15)
);


CREATE TABLE s242552.task (
    id integer NOT NULL,
    name character varying(20),
    description text,
    id_fraction integer NOT NULL,
    id_type integer,
    id_state integer,
    id_privacy integer
);


CREATE SEQUENCE s242552.task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.type_resources (
    id integer NOT NULL,
    name character varying(30),
    cost integer
);



CREATE TABLE s242552.type_ship (
    name character varying(50) NOT NULL,
    hp integer,
    speed integer,
    protection integer,
    cost integer DEFAULT 100 NOT NULL,
    CONSTRAINT cost_check CHECK ((cost >= 100))
);


CREATE TABLE s242552.type_task (
    id integer NOT NULL,
    name_state character varying(30)
);



CREATE TABLE s242552.type_weather (
    id integer NOT NULL,
    name character varying(30)
);


CREATE TABLE s242552.user_account (
    id integer NOT NULL,
    login text,
    password text,
    mail text
);


CREATE SEQUENCE s242552.user_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE s242552.user_battle (
    id_user integer NOT NULL,
    id_battle integer NOT NULL,
    date date,
    id_state integer
);



CREATE TABLE s242552.user_fraction (
    id_fraction integer NOT NULL,
    id_user integer NOT NULL,
    date date NOT NULL,
    id_state integer
);


CREATE TABLE s242552.user_group (
    id_user integer NOT NULL,
    id_group integer NOT NULL
);



CREATE TABLE s242552.users (
    id integer NOT NULL,
    login text,
    password text,
    level integer,
    id_state integer,
    email character varying(30),
    first_name character varying(30),
    last_name character varying(30),
    description text,
    coins integer DEFAULT 0 NOT NULL,
    CONSTRAINT coins_check CHECK ((coins >= 0))
);


CREATE SEQUENCE s242552.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE s242552.vote (
    id_user integer NOT NULL,
    id_result integer NOT NULL
);

CREATE TABLE s242552.voting (
    id integer NOT NULL,
    id_chat integer,
    message text
);


CREATE SEQUENCE s242552.voting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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
-- Name: complain id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.complain ALTER COLUMN id SET DEFAULT nextval('s242552.complain_id_seq'::regclass);


--
-- Name: fraction id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.fraction ALTER COLUMN id SET DEFAULT nextval('s242552.fraction_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.messages ALTER COLUMN id SET DEFAULT nextval('s242552.messages_id_seq'::regclass);


--
-- Name: news id; Type: DEFAULT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.news ALTER COLUMN id SET DEFAULT nextval('s242552.news_id_seq'::regclass);


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
-- Data for Name: base; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.base (id, name_base, id_user, id_system, location_base_x, location_base_y, location_base_z) FROM stdin;
6	test	1	2	530	670	960
8	Base super	1	2	730	370	1160
9	xxxx	1	1	-750	-470	720
10	Testing base	10	2	-750	870	-920
12	Test createBase	10	1	-433	-363	-1414
13	TEST BASE 2	10	1	-1994	-1576	-398
14	TEST BASE 3	10	1	-142	-961	-624
15	Testee	10	1	2211	738	3700
16	Testeeee	10	1	1734	-6889	-8100
17	THY	10	1	-3607	-1915	-5000
18	YAHAHAHA	10	1	-1591	8307	-3593
19	YEPYEP	10	1	6774	9893	-9964
20	Hop hey	12	1	-7285	-8779	-8713
\.


--
-- Data for Name: battle; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.battle (id, name, id_system, date) FROM stdin;
\.


--
-- Data for Name: chat; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.chat (id, name, date) FROM stdin;
1	The Best	2018-11-03
2	test2	2018-11-03
3	test2	2018-11-03
4	Gays	\N
5	Support chat1101544401971037	2018-12-10
6	Support chat111544401971037	2018-12-10
7	testN2	2018-12-10
8	Support chat111544401971037	2018-12-10
9	testasdas	2018-12-10
10	Support chat111544401971037	2018-12-10
11	Support chat111544401971037	2018-12-10
12	Support chat111544401971037	2018-12-10
13	Support chat1011544401971037	2018-12-10
14	Support chat1011544401971037	2018-12-10
15	Support chat1011544401971037	2018-12-10
\.


--
-- Data for Name: chat_user; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.chat_user (id_user, id_chat) FROM stdin;
10	14
10	15
\.


--
-- Data for Name: complain; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.complain (id, id_message, date, state) FROM stdin;
3	\N	2018-12-10	closed
\.


--
-- Data for Name: fraction; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.fraction (id, name_fraction, id_politics, count_player) FROM stdin;
8	name	1	-2
16	testsss	1	1
17	testsssss	1	0
18	Фракция Глебов	1	-2
\.


--
-- Data for Name: group_authority; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.group_authority (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_LEADER
3	ROLE_ADMIN
\.


--
-- Data for Name: groups; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.groups (id, name) FROM stdin;
1	User
2	leader
3	admin
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.messages (id, id_chat, id_user, message) FROM stdin;
1	1	1	Hello, geys
2	1	1	hey, h=who are you?
3	1	1	Hello ouwww :3
4	1	1	Hello ouwww :3
5	1	1	Hello ouwww :3
6	1	1	hey, h=who are you?
7	1	1	I'm Gleb
8	2	1	Hello
9	1	1	string
10	\N	10	ERROR
11	15	1	Что поизошло?
13	15	10	Что поизошло?
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.news (id, name, full_name, date) FROM stdin;
1	Почему все ненавидят шуста	Да потому что он козёл, сука и мразота та еще	2018-11-30 12:56:29.075594
2	Новый патч, который пытается что-то сделать	"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"\n1914 translation by H. Rackham\n\n"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"\nSection 1.10.33 of "de Finibus Bonorum et Malorum", written by Cicero in 45 BC\n\n"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."\n1914 translation by H. Rackham\n\n"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains."\n	2018-12-01 10:15:19.083645
3	Почему все ненавидят шуста 2	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:38:34.468785
4	Почему все ненавидят шуста 2	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:38:40.057171
5	Почему все ненавидят шуста 2	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:38:50.23705
6	Почему все ненавидят шуста 3	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:39:08.919991
7	Почему все ненавидят шуста 4	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:39:12.691906
8	Почему все ненавидят шуста 5	\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et vulputate odio. Duis vestibulum malesuada feugiat. Etiam gravida neque metus, sed dictum massa pulvinar eu. Aenean sit amet ligula convallis, sagittis tellus nec, dignissim mauris. Vivamus et sem diam. In hac habitasse platea dictumst. Morbi augue metus, mattis vitae interdum cursus, rhoncus in felis. In viverra leo quam, sit amet porta ligula ornare eu. Vivamus blandit vitae turpis non facilisis. Integer leo ligula, molestie quis tortor eget, bibendum auctor justo. Nam convallis feugiat faucibus. Fusce molestie vehicula augue, ac egestas arcu placerat a. Mauris sit amet pellentesque nisl. Phasellus a tortor at nibh vestibulum suscipit quis eget eros. Nullam tincidunt purus blandit elementum sollicitudin. Nulla sit amet varius ante.\n\nDonec sagittis neque tellus, at facilisis mauris rhoncus eu. Suspendisse at tortor et nibh gravida feugiat. Sed cursus erat lacus, at suscipit risus volutpat ut. Ut et consequat orci. In finibus hendrerit feugiat. Proin lobortis est eu augue sagittis, a fringilla elit dictum. Proin faucibus nisl ut elementum venenatis.\n\nFusce id pulvinar arcu, vitae scelerisque enim. Nunc sed mauris pulvinar, elementum risus a, consectetur est. Nulla eleifend tristique egestas. Nulla varius lacus ut ex pretium vulputate. Proin aliquam risus nec leo. 	2018-12-01 11:39:16.621826
\.


--
-- Data for Name: persistent_logins; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.persistent_logins (username, series, token, last_used) FROM stdin;
test	OOacAnETHiocnj5hlP62ig==	Qt/LBk7+WfjxcfHegbR3LQ==	2019-01-16 23:17:13.209
test	x/g1WOxwQFSbieXrYB7QiQ==	NAak54CgutXJG9Fxi11gcA==	2019-01-16 23:23:45.811
test	CTachPzOhTcS8R8zoyCOHg==	wI2N8TgD3eOhxaPVBFNosA==	2019-01-16 23:50:17.531
glebbss	pSZ+kDVnUdsIJt34nNHwVQ==	hqxiAscdUOcLkFnZdRcspA==	2018-12-10 02:43:15.763
test	i3pjVoiUitr7LhgLQUlw1A==	xUwlYHPbP85NWB5Tf3gFSw==	2019-01-17 02:02:55.282
test	USbAJR7DK7Eg8n0tGA7Ovw==	IzgwuI62CthSEOC7Bxk/oQ==	2019-01-17 03:10:33.615
test	WRLVlTw4cZC67PAb5a71ig==	/HstO5VQzElqllU1Pf3xqg==	2019-01-17 04:01:07.858
test	+IYAY3YLCEFZuolfhMfEZA==	gLPFIsCAkRhRtMNQMQ34QA==	2019-01-17 05:51:46.353
test	t5CDlQ4X2RzzxjuHbPIroQ==	Xq4UyCGJB/54CRUPUdNSfA==	2019-01-17 06:04:14.343
test	DeQeEDuBJeZh+NJj8EBxTg==	bH2FWp9Nncvtuf/p6NAoFQ==	2019-01-17 06:11:59.322
test	06un9nUIPa81RtvLW+6qSA==	mTAq1BLjCVoU3swmR9XLiA==	2019-01-17 06:22:41.895
test	IoKcB5j4qw6+XNR7cfAEDw==	LcPa56Nikig/mnQA2ILSGA==	2019-01-17 06:25:12.075
test	npwMK451e1oi9rPYmn6E+A==	lhPZYzT9R6olxKNCTTC8Bw==	2019-01-17 06:31:54.677
test	5nlNs3Dosoq4+10GdyblkQ==	7oeZwMuZ4yOc6jAFUf06gQ==	2019-01-16 23:16:58.167
test	4OUUiR2CIrnZCDKLB+rNSg==	319s36J600V9wplFGOQYqg==	2019-01-16 23:56:52.671
test	PCN0fE5gbsazeOypKPeZBw==	d5w0rSlTkzbxde3K3mn7ow==	2019-01-17 00:41:35.945
glebbss	EaCn6DSrk0vybzvVAWVdYQ==	yCorMZiIVXxwxGNGq9bZsg==	2018-12-10 02:16:27.059
test	nX3w7hCcX5A7ongfI7qMkQ==	hHAeGa+nMtwHlDBJl8xu/A==	2019-01-17 01:06:47.263
test	cz3AdkWcUNYSNyRW8BskGQ==	opKFdIduIX12b/4TVc6Mog==	2019-01-17 02:00:12.911
test	Ioi3hxQH6ZQVp7POwzgeEw==	HNY0dygZDXbS35Kll3p3xQ==	2019-01-17 02:05:30.051
test	tZz+/xs4ANoXHr0/tYqt0Q==	2LUMJ+lgKSMPc7kj1FouGA==	2019-01-17 03:12:19.737
test	lvyQnxRODoOzYlKqmi6MWQ==	/yxVdau5gEJkKP+rw95fmw==	2019-01-17 06:09:38.452
test	8wQI1Hi+6gse2jfQwAB99w==	9R98abwBCXp5ZXBrQXDxkg==	2019-01-17 06:23:21.065
test	uU2cd4CS0jzO85yNFDRRag==	LBt9qxbZclsD+FAMJq0cJQ==	2019-01-17 06:27:56.274
test	GjVVPVyEuVVZu9mKB3oAgw==	pi6jQToUGsbsYxlgMExeuQ==	2019-01-17 06:33:52.714
\.


--
-- Data for Name: planet; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.planet (id, name, id_system, location_planet_x, location_planet_y, id_user, type_weather, location_planet_z, rplanet, img) FROM stdin;
49	Planet testt	1	57900000	57900000	10	1	57900000	7900	planet1.jpg
48	Planet test	1	0	0	10	1	0	6955	planet0.jpg
\.


--
-- Data for Name: politics; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.politics (id, name_politics) FROM stdin;
1	USSR
\.


--
-- Data for Name: resource; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.resource (id, name, id_planet, count, type) FROM stdin;
92	test	48	3	1
93	test2	48	1	2
\.


--
-- Data for Name: results; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.results (id, id_voting, name) FROM stdin;
2	1	tree
1	1	ironKek
\.


--
-- Data for Name: ship; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.ship (id, hp, name_ship, id_base, id_system, id_user, location_ship_x, location_ship_y, speed, protection, state, type_ship, location_ship_z, rotation_ship_x, rotation_ship_y, rotation_ship_z) FROM stdin;
60	700	HSHSHSH	10	2	10	12132	12796	800	30	2	Стервятник	-890	0.819999993	0.74000001	0
61	700	YAHYAY	10	2	10	-229	432	800	30	1	Стервятник	-252	-6.57000017	16.6800003	0.550000012
67	300	MANYA	20	1	12	-7215	-8709	800	30	2	Стервятник	-8643	0	0	0
58	100	SHUSHTT	9	1	1	-413	-954	800	30	2	Стервятник	1426	1.39999998	1.39999998	1.39999998
64	1500	Hop hey	10	2	10	91	168	450	50	2	Титан	12	-0.189999998	-3.17000008	0
45	300	asdas	9	1	1	200	200	400	30	2	Стервятник	3180	0.0013	0	0
52	300	YEAH	9	1	1	-380	-1628	800	30	2	Стервятник	2963	1.79999995	1.60000002	0.280000001
54	300	AUAUA	6	2	1	529	523	800	30	2	Стервятник	4835	97	97	97
50	100	YAPYAP	9	1	1	-40	-231	800	30	1	Стервятник	2	-0.280000001	-4.63999987	0
47	0	Yep	6	2	1	100	100	410	30	1	Стервятник	862	0	0	0
59	1000	Hohoho	10	2	10	1925	-31	970	30	2	Стервятник	1333	6.55000019	-19.0699997	0
56	0	th you	8	2	1	1690	-181	800	30	1	Стервятник	1426	-36.1399994	-2.70000005	-7.07999992
66	1500	Nu co	19	1	10	6457	9549	450	50	2	Титан	-9431	0.779999971	-0.74000001	0
55	300	HEYY	6	2	1	540	679	800	30	2	Стервятник	1529	0.00249999994	0	0
51	300	YAPYAP	9	1	1	-1755	-963	800	30	2	Стервятник	880	0.781599998	-2.68000007	1.51999998
53	300	AUAUA	8	2	1	1420	794	800	30	2	Стервятник	3619	8.42000008	3.13000011	3.98000002
46	300	WHatSup	9	1	1	796	1658	400	30	2	Стервятник	-705	1.80999994	4.63999987	0.839999974
65	400	\N	10	2	10	200	-220	450	50	2	Титан	189	0.239999995	0.850000024	0
57	200	Opatya	8	2	1	1550	380	810	30	1	Стервятник	1170	0	0	0
\.


--
-- Data for Name: ship_battle; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.ship_battle (id_ship, id_battle) FROM stdin;
\.


--
-- Data for Name: state_complain; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_complain (name) FROM stdin;
new
closed
processing
\.


--
-- Data for Name: state_privacy; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_privacy (id, name) FROM stdin;
1	public
2	private
\.


--
-- Data for Name: state_ship; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_ship (id, name, name_normal) FROM stdin;
2	ready	В боевой готовности
1	destroyed	Сломан
\.


--
-- Data for Name: state_task; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_task (id, name_state) FROM stdin;
1	created
\.


--
-- Data for Name: state_user; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_user (id, name) FROM stdin;
\.


--
-- Data for Name: state_user_battle; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_user_battle (id, name) FROM stdin;
1	В бою
2	Вышел из боя
\.


--
-- Data for Name: state_user_fraction; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.state_user_fraction (id, name, name_normal) FROM stdin;
3	candidate	Кандидат
1	leader	Лидер
2	player	Игрок
4	left	Вышел
\.


--
-- Data for Name: system; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.system (id, name_system) FROM stdin;
1	Test system
2	Test#32412
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.task (id, name, description, id_fraction, id_type, id_state, id_privacy) FROM stdin;
4	Create test	Test descr	8	1	1	1
5	Create test	Test descr	8	1	1	2
6	Test privacy	test privacy	8	1	\N	2
7	Tes	test privacy	8	1	1	1
\.


--
-- Data for Name: type_resources; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.type_resources (id, name, cost) FROM stdin;
1	Железо	10
2	Уран	30
\.


--
-- Data for Name: type_ship; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.type_ship (name, hp, speed, protection, cost) FROM stdin;
Стервятник	300	800	30	100
Крейсер	700	400	50	300
Титан	1500	450	50	500
\.


--
-- Data for Name: type_task; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.type_task (id, name_state) FROM stdin;
1	test
\.


--
-- Data for Name: type_weather; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.type_weather (id, name) FROM stdin;
1	снег
\.


--
-- Data for Name: user_account; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.user_account (id, login, password, mail) FROM stdin;
1	test	$2a$10$aJ7a.8gk/sPlyQsl0I5co./MVJo/6dJAivJKKgsCcgtGiqVlnBoAO	\N
10	glebb	$2a$10$jU0rTXRWZPRDFKea92.sp.tvdKc.qMckDhmcGHQkxz5fDkOF1JXe.	\N
12	gleb.larochkin@gmail.com		gleb.larochkin@gmail.com
13	username=glebb&password=gleb	$2a$10$6UnZudjT7T3CeZVCvVuGAOt5eMMXShqCWi5s1b61zTb21ckHeYESe	\N
14	username=glebbs&password=gleb	$2a$10$A.eR8qxBf27LzNaDkH1KIeryGR6pK9sgIdJ2E.N1ipYeZvej0pQ.q	\N
15	glebbss	$2a$10$9lBWLvlKN5HTOXObi.wWBepCHjh.n0NT2tEjMYX9e4POjpffUy2kK	\N
\.


--
-- Data for Name: user_battle; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.user_battle (id_user, id_battle, date, id_state) FROM stdin;
\.


--
-- Data for Name: user_fraction; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.user_fraction (id_fraction, id_user, date, id_state) FROM stdin;
18	10	2019-01-17	1
18	1	2019-01-17	2
18	12	2019-01-17	3
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.user_group (id_user, id_group) FROM stdin;
10	1
10	2
1	1
1	3
12	1
13	1
14	1
15	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.users (id, login, password, level, id_state, email, first_name, last_name, description, coins) FROM stdin;
1	Test	\N	1	\N	\N	Глеб	Ларочкин	\N	5820
12	Hacker	\N	1	\N	\N	Hacker	Hacker	\N	8400
10	Gleb	\N	\N	\N	\N	Глеб	Ларочкин	\N	33420
13	\N	\N	1	\N	\N	\N	\N	\N	10000
14	\N	\N	1	\N	\N	\N	\N	\N	10000
15	\N	\N	1	\N	\N	\N	\N	\N	10000
\.


--
-- Data for Name: vote; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.vote (id_user, id_result) FROM stdin;
1	1
\.


--
-- Data for Name: voting; Type: TABLE DATA; Schema: s242552; Owner: s242552
--

COPY s242552.voting (id, id_chat, message) FROM stdin;
1	1	Voting me
2	1	Test voting
3	2	Test voting with chat 2
\.


--
-- Name: base_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.base_id_seq', 20, true);


--
-- Name: battle_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.battle_id_seq', 3, true);


--
-- Name: chat_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.chat_id_seq', 15, true);


--
-- Name: complain_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.complain_id_seq', 3, true);


--
-- Name: fraction_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.fraction_id_seq', 18, true);


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.messages_id_seq', 13, true);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.news_id_seq', 8, true);


--
-- Name: planet_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.planet_id_seq', 49, true);


--
-- Name: resource_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.resource_id_seq', 93, true);


--
-- Name: results_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.results_id_seq', 2, true);


--
-- Name: ship_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.ship_id_seq', 67, true);


--
-- Name: task_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.task_id_seq', 7, true);


--
-- Name: user_account_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.user_account_id_seq', 15, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.users_id_seq', 5, true);


--
-- Name: voting_id_seq; Type: SEQUENCE SET; Schema: s242552; Owner: s242552
--

SELECT pg_catalog.setval('s242552.voting_id_seq', 3, true);


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
-- Name: news news_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


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
-- Name: state_complain state_complain_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.state_complain
    ADD CONSTRAINT state_complain_pkey PRIMARY KEY (name);


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
-- Name: type_ship type_ship_pkey; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.type_ship
    ADD CONSTRAINT type_ship_pkey PRIMARY KEY (name);


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
-- Name: user_fraction user_fraction_pk; Type: CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.user_fraction
    ADD CONSTRAINT user_fraction_pk PRIMARY KEY (id_user);


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
-- Name: complain complain_state_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.complain
    ADD CONSTRAINT complain_state_fkey FOREIGN KEY (state) REFERENCES s242552.state_complain(name);


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
    ADD CONSTRAINT messages_id_user_fkey FOREIGN KEY (id_user) REFERENCES s242552.users(id) ON DELETE CASCADE;


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
-- Name: ship ship_type_ship_fkey; Type: FK CONSTRAINT; Schema: s242552; Owner: s242552
--

ALTER TABLE ONLY s242552.ship
    ADD CONSTRAINT ship_type_ship_fkey FOREIGN KEY (type_ship) REFERENCES s242552.type_ship(name);


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

