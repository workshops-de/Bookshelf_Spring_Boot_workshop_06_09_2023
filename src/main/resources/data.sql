DELETE FROM book;

INSERT INTO book (isbn, title, author, description) VALUES ('978-0201633610', 'Design Patterns', 'Erich Gamma', 'Mit Design Patterns lassen sich wiederkehrende Aufgaben in der objektorientierten Softwareentwicklung effektiv lösen.');
INSERT INTO book (isbn, title, author, description) VALUES ('978-3826655487', 'Clean Code', 'Robert C. Martin', 'Das einzige praxisnahe Buch, mit dem Sie lernen, guten Code zu schreiben!');
INSERT INTO book (isbn, title, author, description) VALUES ('978-3836211161', 'Coding for Fun', 'Gottfried Wolmeringer', 'Dieses unterhaltsam geschriebene Buch führt Sie spielerisch durch die spektakuläre Geschichte unserer Blechkollegen.');

DELETE FROM bookshelf_user;
INSERT INTO bookshelf_user (username, password, firstname, lastname, role)
    VALUES ('dbUser', '$2a$10$JiCdx64U5h4NfmGzBw7xfeQqtQnUmGnuCi4SN3f366eizwnTfaLTy', 'Default', 'Default', 'ROLE_USER');
INSERT INTO bookshelf_user (username, password, firstname, lastname, role)
    VALUES ('dbAdmin', '$2a$10$WXcHBgD6aZvUXpw/7R2xdu9vJ7jOCrRJ6L6uLp2GvDhyZsAJIUJNi', 'Default', 'Default', 'ROLE_ADMIN');
