Sistema DSCommerce

Visão geral do sistema
O sistema contém:
 * cadastro de usuário;
 * cadastro de produtos e suas categorias.

Cada usuário possui:
 * nome;
 * email;
 * telefone;
 * data de nascimento; e
 * senha de acesso.

Os atributos dos produtos são:
 * nome;
 * descrição;
 * preço; e
 * imagem.

O sistema apresenta um catálogo de produtos, os quais podem ser filtrados pelo nome do produto. A partir desse catálogo, o usuário pode selecionar um produto para ver seus detalhes e para
decidir se o adiciona a um carrinho de compras.

O usuário pode incluir e remover itens do carrinho de compra, bem como alterar as quantidades de cada item.

Uma vez que o usuário decida encerrar o pedido, este deve então ser salvo no sistema com o status de "aguardando pagamento". Os dados de um pedido são:
 * instante em que ele foi salvo;
 * status; e
 * uma lista de itens, onde cada item se refere a um produto e sua quantidade no
pedido.

O status de um pedido pode ser:
 * aguardando pagamento;
 * pago;
 * enviado;
 * entregue; e
 * cancelado.

Quando o usuário paga por um pedido, o instante do pagamento deve ser registrado.

Os usuários do sistema podem ser clientes ou administradores.
Usuários não identificados podem:
 * se cadastrar no sistema;
 * navegar no catálogo de produtos e no carrinho de compras.

Clientes podem:
 * atualizar seu cadastro no sistema;
 * registrar pedidos; e
 * visualizar seus próprios pedidos.

Usuários administradores tem acesso à área administrativa onde podem:
 * acessar os cadastros de usuários;
 * produtos; e
 * categorias.
