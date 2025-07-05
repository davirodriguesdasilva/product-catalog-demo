/*==================================================================
  Tabela       : TB_CATEGORY
  Propósito    : Armazenar categorias de produtos
==================================================================*/

-- Criar tabela
CREATE TABLE TB_CATEGORY (
  ID   NUMBER(19)     NOT NULL,
  NAME VARCHAR2(255)  NOT NULL,
  CONSTRAINT PK_CATEGORY PRIMARY KEY (ID)
    USING INDEX TABLESPACE USERS,
  CONSTRAINT UK_CATEGORY_NAME UNIQUE (NAME)
);

-- Criar sequência
CREATE SEQUENCE SQ_CATEGORY
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

-- Carga de dados para categorias
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO TB_CATEGORY (ID, NAME)
    VALUES (
      SQ_CATEGORY.NEXTVAL,
      'Categoria_' || TO_CHAR(i)
    );
  END LOOP;
  COMMIT;
END;

