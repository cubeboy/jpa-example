insert into com_code (group_id, code_id, code_name)
values
('GRADE', 'A', 'A Grade'),
('GRADE', 'B', 'B Grade'),
('GRADE', 'C', 'C Grade'),
('LEVEL', '01', 'Gold'),
('LEVEL', '02', 'Silver'),
('LEVEL', '03', 'Bronze')
;

insert into team (team_id, name) values (201, 'FirstTeam');

insert into member (name,member_id, zip_code, age, team_id, member_grade, member_level) values
('user101',101, 'zip101', 1, 201, 'A', '02');


insert into member (name,member_id, zip_code, age, team_id) values
('user102',102, 'zip102', 2, 201),
('user103',103, 'zip103', 3, 201),
('user104',104, 'zip104', 4, 201),
('user105',105, 'zip105', 5, 201),
('user106',106, 'zip105', 6, 201),
('user107',107, 'zip107', 7, 201),
('user108',108, 'zip108', 8, 201),
('user109',109, 'zip109', 9, 201),
('user110',110, 'zip110', 10, 201),
('user111',111, 'zip111', 11, 201),
('user112',112, 'zip112', 12, 201),
('user113',113, 'zip113', 13, 201),
('user114',114, 'zip114', 14, 201),
('user115',115, 'zip115', 15, 201),
('user116',116, 'zip116', 16, 201),
('user117',117, 'zip117', 17, 201),
('user118',118, 'zip118', 18, 201),
('user119',119, 'zip119', 19, 201),
('user120',120, 'zip120', 20, 201)
;


