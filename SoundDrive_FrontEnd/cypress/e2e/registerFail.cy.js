describe('Register', () => {
    it('should open the register page and register the user', () => {
        cy.visit('http://localhost:3000/register');
        cy.get('#name').type('Veiro');
        cy.get('#email').type('verio@gmail.com');
        cy.get('#password').type('Veiro!1234567');
        cy.get('#ButtonRegister').click();
        cy.contains('Email already in use')
    });  
});