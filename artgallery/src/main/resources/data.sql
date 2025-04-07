INSERT INTO 권한 (authority_name) VALUES ('ADMIN');
INSERT INTO 권한 (authority_name) VALUES ('USER');

INSERT INTO 작품 ( title, img_url, description, completion_date, upload_date, artist_id )
VALUES
(),
(),
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 아티스트 ( name, profile_img )
VALUES
(),
(),
(),
(),
(),
(),
();

INSERT INTO 작가전시회 ( title, poster_url, description, start_date, end_date, price )
VALUES
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 수상경력 ( award, year, artist)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 채팅 ( user_name, text, date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 답글 ( text, creation_date, user_name, community_id)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 커뮤니티 ( text, likes, upload_date, modify_date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO contact ( name, title, email, message)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 드로잉 ( img_url, title, description, completion_date, is_complete, usergallery_id)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 굿즈상품 ( name, img_url, description, price, stock)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 굿즈장바구니 ( amount, sum, goods_id, user_name)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 공지사항 ( title, content, created_date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 굿즈구매내역 ( goods_sum, goods_id, user_name, purchase_date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 리뷰 ( text, userName, goods_id, created_at)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 전시티켓 ( count, select_date, artistGallery_id, purchase_date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 사용자 ( user_id, password, nickname, real_name, email, birthday, address, enrolment_date, point,gender, user_authority)
VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO 사용자전시회 ( title, poster_url, price, description,start_date,end_date)
VALUES
(),
(),
(),
(),
(),
(),
(),
();



