/*==================================================================
  Tabela       : TB_PRODUCT
  Propósito    : Armazenar produtos do catálogo
==================================================================*/

-- Criar tabela
CREATE TABLE TB_PRODUCT (
  ID            NUMBER(19)      NOT NULL,
  CATEGORY_ID   NUMBER(19)      NOT NULL,
  NAME          VARCHAR2(255)   NOT NULL,
  IMAGE_URL     VARCHAR2(512),
  PRICE         NUMBER(10,2)    NOT NULL,
  DESCRIPTION   VARCHAR2(1000),
  RATING_AVG    NUMBER(2,1),
  CONSTRAINT PK_PRODUCT PRIMARY KEY (ID)
    USING INDEX TABLESPACE USERS,
  CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (CATEGORY_ID)
    REFERENCES TB_CATEGORY (ID),
  CONSTRAINT CHK_PRODUCT_PRICE CHECK (PRICE >= 0),
  CONSTRAINT CHK_PRODUCT_RATING CHECK (RATING_AVG BETWEEN 1 AND 5)
);

-- Índices de apoio para filtragem
CREATE INDEX IDX_PRODUCT_CATEGORY  ON TB_PRODUCT (CATEGORY_ID);
CREATE INDEX IDX_PRODUCT_PRICE     ON TB_PRODUCT (PRICE);
CREATE INDEX IDX_PRODUCT_RATING    ON TB_PRODUCT (RATING_AVG);

-- Criar sequência
CREATE SEQUENCE SQ_PRODUCT
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

-- Carga de dados para produtos
BEGIN
  FOR i IN 1..1000 LOOP
    INSERT INTO TB_PRODUCT (
      ID,
      CATEGORY_ID,
      NAME,
      IMAGE_URL,
      PRICE,
      DESCRIPTION,
      RATING_AVG
    )
    VALUES (
      SQ_PRODUCT.NEXTVAL,
      MOD(i, 10) + 1,
      'Produto_' || TO_CHAR(i),
      'https://placehold.co/600x400',
      ROUND(DBMS_RANDOM.VALUE(10, 1000), 2),
      'Descrição do produto número ' || TO_CHAR(i),
      ROUND(DBMS_RANDOM.VALUE(1, 5), 1)
    );
  END LOOP;
  COMMIT;
END;
