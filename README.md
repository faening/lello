# GDD – Lello: Diário Gamificado de Autocuidado

## Estória

O usuário embarca em uma jornada de autocuidado, tendo como companheira a capivara Lello. A cada dia, ao registrar seus hábitos e emoções, o usuário cuida não só de si, mas também do seu mascote, que evolui e recebe itens conforme o progresso nas atividades. O objetivo é tornar o acompanhamento terapêutico, a adesão ao tratamento e o registro dos sentimentos mais leves, acessíveis e divertidos, recompensando a disciplina e o autoconhecimento com novas possibilidades de personalização do mascote.

## Fragmentos da Estória

Cada registro nos diários representa um passo no processo de autoconhecimento e cuidado diário. Ao preencher os diários de humor, alimentação, sono e medicação, o usuário fortalece seu vínculo com Lello e conquista pequenas vitórias cotidianas, visíveis por meio de conquistas, moedas e itens para o mascote. O app reforça o progresso contínuo, premiando a regularidade, a atenção aos detalhes e o engajamento nas próprias rotinas de saúde mental.

## Especificações

### Estrutura do Jogo

O aplicativo é organizado em torno de **quatro diários principais**:

- **Diário de Humor**
- **Diário de Sono**
- **Diário de Alimentação**
- **Diário de Medicamentos**

O usuário pode preencher cada diário dentro do seu respectivo intervalo de tempo (respawn), ganhando moedas que podem ser trocadas por itens de personalização para Lello, a mascote. Se o usuário preencher todos os quatro diários em um mesmo dia, recebe um bônus especial de moedas.

Cada diário é composto por 3 ou 4 telas. As **duas primeiras telas de cada diário são obrigatórias**: ao preenchê-las, o usuário ganha **50 moedas**. Telas adicionais trazem perguntas ou opções extras; cada grupo respondido gera **+5 moedas**.

### Estrutura dos Diários e Tempos de Respawn

| Diário              | Respawn para ganhar moedas | Tela obrigatória | Telas opcionais | Moedas por obrigatória | Moedas por grupo extra |
|---------------------|---------------------------|------------------|-----------------|------------------------|------------------------|
| Humor               | 6 horas                   | 2                | 1–2             | 50                     | 5                      |
| Alimentação         | 8 horas                   | 2                | 1–2             | 50                     | 5                      |
| Sono                | 8 horas                   | 2                | 1–2             | 50                     | 5                      |
| Medicamentos        | 24 horas                  | 2                | 1–2             | 50                     | 5                      |
| Bônus de 4 diários  | 24 horas                  | –                | –               | 50                     | –                      |

- Mesmo que o usuário preencha vários diários seguidos, só receberá moedas novamente após o tempo de respawn.
- O preenchimento completo dos 4 diários em um dia concede um bônus extra de moedas (com respawn de 24h).

### Estrutura do Cenário

O mascote Lello é o centro visual do app. Os cenários mudam de acordo com os itens adquiridos pelo usuário (como fundos, acessórios, brinquedos e alimentos para Lello). Cada novo item adquirido personaliza a tela do mascote e reforça o senso de progresso e conquista.

### Mecânicas do Jogador

- O usuário registra informações em diários diários (humor, alimentação, sono, medicamentos).
- Ganha moedas ao completar as etapas obrigatórias e opcionais de cada diário.
- Troca moedas por itens cosméticos para personalizar o mascote.
- Pode consultar o histórico de diários, conquistas e moedas.
- O mascote reage a interações, mudanças e itens conquistados.

### Atributos do Mascote

- **Aparência**: Personalizável conforme itens adquiridos.
- **Vitalidade/Felicidade**: Pode ser representada visualmente, mas não limita funções do app (não existe “perda”).
- **Conquistas**: Desbloqueadas ao atingir metas (ex: sequência de dias, primeiros itens, completar todos os diários em um dia, etc).

### Tipos de Itens (Power-ups/Cosméticos)

- Itens de personalização do mascote (acessórios, brinquedos, comidas, fundos, sons).
- Não existem power-ups clássicos; os itens são puramente cosméticos e reforçam o vínculo entre o usuário e Lello.

### Sistema de Pontuação e Moedas

- **50 moedas** por preencher os itens obrigatórios de um diário.
- **5 moedas** para cada grupo de itens opcionais preenchido.
- **Bônus de 50 moedas** ao preencher os 4 diários no mesmo dia.
- Moedas podem ser usadas exclusivamente para aquisição de itens cosméticos para Lello.

### Progressão e Balanceamento

- O sistema de respawn impede o acúmulo excessivo de moedas, garantindo progressão constante sem exploração.
- O usuário é incentivado a registrar todos os diários ao longo do dia para maximizar recompensas e personalização.
- Não há finais, derrotas nem penalidades: o objetivo é a constância e o autocuidado, não a competição.

### Especificação de Som e Gráficos

- **Gráficos**: Visual leve, amigável, com destaque para o mascote e seus itens. O cenário se transforma conforme a personalização escolhida.
- **Som**: Trilha sonora suave e relaxante no app, podendo variar entre ambientes (home, diário, loja). Efeitos sonoros para interações com Lello e ao receber recompensas.

### Opções do Menu

O app conta com um menu inferior de cinco abas:

1. **Início**: Resumo dos bônus e progresso.
2. **Diários**: Lista de diários preenchidos, com filtro de datas.
3. **Lello (Central)**: Acesso à tela do mascote, loja e conquistas.
4. **Remédios**: Cadastro e gestão dos medicamentos.
5. **Perfil**: Dados do usuário, preferências e configurações.

---

Se quiser, posso complementar com wireframes, exemplos de telas ou adicionar detalhes sobre os fluxos internos!  
Se quiser um resumo do histórico de decisões do projeto para anexar ao GDD, também posso gerar.

Se precisar do texto pronto para copiar e colar, só avisar! Posso ajustar tom, formato e nível de detalhe conforme seu público do GitHub.
