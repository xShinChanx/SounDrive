describe('LogIn', () => {
    it('Log In with wrong details', () => {
        cy.visit('http://localhost:3000/Login');
        cy.get('#email').type('adel@gmail.com');
        cy.get('#password').type('yeet');
        cy.get('#ButtonLogin').click();
        cy.contains('Invalid Credential')
    });  
});