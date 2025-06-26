package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.journal.JournalCategoryEntity

internal object JournalCategorySeed: Seed<JournalCategoryEntity> {
    override val data = listOf(
        JournalCategoryEntity(
            id = 1,
            name = "Humor",
            shortDescription = "Registre suas emoções e compreenda melhor seus estados emocionais",
            longDescription = """
                       O Diário de Humor é uma ferramenta central do aplicativo, inspirada na Ficha de Regulação Emocional do Treinamento 
                       em DBT (Terapia Comportamental Dialética). Com ele, você poderá registrar suas emoções ao longo do dia, identificar 
                       padrões e refletir sobre os fatores que influenciam seu estado emocional.
                       
                       Esse registro contínuo fortalece a percepção emocional, promove o autoconhecimento e contribui para um  
                       acompanhamento mais eficaz durante as sessões terapêuticas. Importante: este recurso não substitui o trabalho com 
                       o seu terapeuta, mas o complementa, fortalecendo o vínculo e aprofundando o processo de cuidado.
                    """.trimIndent(),
            blocked = true,
            active = true
        ),
        JournalCategoryEntity(
            id = 2,
            name = "Medicamentos",
            shortDescription = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento",
            longDescription = """
                        O Diário de Medicamentos foi desenvolvido para ajudar no acompanhamento do uso contínuo de medicamentos reguladores. 
                        Ele permite registrar a ingestão diária, anotar possíveis efeitos colaterais e acompanhar como o organismo está se 
                        adaptando ao tratamento.
                        
                        Esse monitoramento é essencial, especialmente durante períodos de ajuste de dose ou troca de medicação, oferecendo 
                        dados valiosos para o próprio paciente e para o psicólogo. Dessa forma, é possível identificar com mais clareza 
                        dificuldades na adesão ou na adaptação, facilitando uma eventual reavaliação psiquiátrica e promovendo um cuidado 
                        mais seguro e eficaz.
                    """.trimIndent(),
            blocked = true,
            active = true
        ),
        JournalCategoryEntity(
            id = 3,
            name = "Sono",
            shortDescription = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional",
            longDescription = """
                        O Diário de Sono foi criado para acompanhar a qualidade e a regularidade do seu descanso, ajudando a entender como 
                        os hábitos de sono influenciam diretamente seu humor, energia e equilíbrio emocional.
                        
                        Registrar seus horários de dormir e acordar, assim como a qualidade percebida do sono, permite identificar padrões 
                        que possam estar prejudicando sua saúde mental. Com essas informações, é possível fazer ajustes na rotina e 
                        promover um descanso mais reparador, contribuindo para uma vida emocional mais estável e saudável.
                    """.trimIndent(),
            blocked = true,
            active = true
        ),
        JournalCategoryEntity(
            id = 4,
            name = "Alimentação",
            shortDescription = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional",
            longDescription = """
                        O Diário de Alimentação tem como objetivo acompanhar seus hábitos alimentares e ajudar na compreensão da sua 
                        relação com a comida. Muitas vezes, alterações no padrão alimentar estão diretamente ligadas ao estado emocional, 
                        como a perda de apetite em quadros depressivos ou episódios de compulsão durante crises de ansiedade.
                        
                        Ao registrar suas refeições e perceber padrões, você desenvolve maior consciência sobre como a alimentação 
                        influencia seu bem-estar emocional. Esse diário é uma ferramenta de apoio para promover escolhas mais saudáveis 
                        e alinhadas com o cuidado da sua saúde mental.
                    """.trimIndent(),
            blocked = true,
            active = true
        )
    )
}