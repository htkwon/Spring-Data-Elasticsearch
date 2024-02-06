INSERT INTO user(id, user_name, password) VALUES
(1,'username1','password1'),
(2,'username2','password2'),
(3,'username3','password3');

INSERT INTO article(id, title,content, user_id,article_enum) VALUES
(1,'title1','content1', 1,'FREE'),
(2,'title2','content2', 3,'QUESTION'),
(3,'title3','content3', 2,'QUESTION'),
(4,'title4','content4', 1,'QUESTION'),
(5,'title5','content5', 3,'QUESTION');
