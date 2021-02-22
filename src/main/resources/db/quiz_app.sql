CREATE TABLE user_info
(
    user_id  SERIAL PRIMARY KEY,
    user_full_name    varchar(50) NOT NULL,
    user_email   varchar(50) NULL,
    phone_number varchar(11) NULL,
    user_level   integer DEFAULT 1,
    user_point   bigint	 DEFAULT 0,
    user_image_url varchar NULL,
    learning_streaks integer DEFAULT 0
);

CREATE TABLE account
(
    account_id  SERIAL PRIMARY KEY,
    user_name  varchar(24) NOT NULL,
    password   varchar(24) NOT NULL,
    role       varchar(10) NOT NULL,
    user_id  bigint REFERENCES user_info (user_id)
);
CREATE TABLE topic
(
    topic_id SERIAL PRIMARY KEY,
    topic_name varchar(50) NOT NULL,
    topic_description varchar NULL,
    user_id  bigint REFERENCES user_info (user_id)
);

CREATE TABLE sub_topic
(
    sub_topic_id SERIAL PRIMARY KEY,
    sub_topic_name varchar(50) NOT NULL,
    sub_topic_description varchar NULL,
    topic_id  bigint REFERENCES topic (topic_id)
);

CREATE TABLE quiz
(
   quiz_id SERIAL PRIMARY KEY,
   quiz_name    varchar(50) NOT NULL,
   quiz_description VARCHAR NULL,
   sub_topic_id  bigint REFERENCES sub_topic(sub_topic_id)
);
CREATE TABLE question
(
    question_id SERIAL PRIMARY KEY,
    question_name varchar(50) NOT NULL,
    question_description varchar NULL,
    question_point integer NOT NULL,
    question_image_url varchar NULL,
    question_answer_A varchar(50) NOT NULL,
    question_answer_B varchar(50) NOT NULL,
    question_answer_C varchar(50) NOT NULL,
    question_answer_D varchar(50) NOT NULL,
    question_answer_correct varchar(50) NOT NULL,
    quiz_id  bigint REFERENCES quiz(quiz_id)

);
CREATE TABLE user_quiz
(

    user_id  bigint REFERENCES user_info (user_id),
    quiz_id  bigint REFERENCES quiz (quiz_id),
    is_finished boolean DEFAULT FALSE,
    current_question_id integer NULL,
    rating integer NULL,
    PRIMARY KEY(user_id,quiz_id)
);