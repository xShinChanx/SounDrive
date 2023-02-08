describe('LogIn', () => {
    it('should open the Log In page', () => {
        cy.visit('http://localhost:3000/Login');
        cy.get('#email').type('adel@gmail.com');
        cy.get('#password').type('yah');
        cy.get('#ButtonLogin').click();
        cy.url().should('eq', 'http://localhost:3000/home/user')
    });  
});