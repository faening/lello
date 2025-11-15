package io.github.faening.lello.feature.settings.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.settings.SettingsViewModel
import io.github.faening.lello.feature.settings.model.TermsListItem

@Composable
fun SettingsTermsAndPrivacyScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    SettingsTermsAndPrivacyScreenContent(onBack = onBack)
}

@Composable
private fun SettingsTermsAndPrivacyScreenContent(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsTermsAndPrivacyTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            Text(
                text = "Termos de uso",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            SettingsTermsAndPrivacyListSection(
                title = "",
                items = listOf(
                    TermsListItem(
                        content = "Bem-vindo(a) ao Lello."
                    ),
                    TermsListItem(
                        content = "Pedimos que leia este documento com atenção. Ao usar o aplicativo Lello (\"App\"), você concorda e se vincula a estes Termos de Uso e à nossa Política de Privacidade. Se você não concordar com alguma parte, não deverá utilizar o aplicativo."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "1. O serviço Lello",
                items = listOf(
                    TermsListItem(
                        content = "O Lello é um aplicativo de diário pessoal, projetado para ser um espaço seguro e privado onde você pode registrar seus sentimentos, gatilhos, rotinas e outros eventos relevantes. O objetivo principal do Lello é servir como uma ferramenta de apoio para pessoas em acompanhamento psicológico, facilitando o registro de informações que podem ser úteis durante a terapia."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "2. Aviso importante sobre saúde mental",
                items = listOf(
                    TermsListItem(
                        content = "O Lello NÃO é um substituto para atendimento profissional."
                    ),
                    TermsListItem(
                        content = "Nossa regra mais importante é a transparência: o Lello é uma ferramenta de registro e não substitui, em hipótese alguma, o diagnóstico, aconselhamento ou tratamento de um terapeuta, psicólogo, psiquiatra ou qualquer outro profissional de saúde qualificado."
                    ),
                    TermsListItem(
                        content = "O aplicativo não foi projetado para diagnosticar, prevenir ou tratar qualquer condição de saúde mental. O Lello nunca irá analisar seus registros para fornecer \"insights\", \"melhorias\" ou qualquer tipo de conselho."
                    ),
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "3. Uso do aplicativo e suas responsabilidades",
                items = listOf(
                    TermsListItem(
                        title = "Segurança da Conta",
                        content = "Você é responsável por proteger o acesso ao seu dispositivo. O Lello oferece proteção por senha ou biometria, e recomendamos fortemente seu uso para proteger a privacidade dos seus registros."
                    ),
                    TermsListItem(
                        title = "Seu Conteúdo",
                        content = "Todo o conteúdo que você insere no Lello (textos, registros de humor, etc.) é de sua inteira propriedade e responsabilidade."
                    ),
                    TermsListItem(
                        title = "Backup (opcional)",
                        content = "O Lello oferece um recurso opcional de backup no seu Google Drive pessoal. A ativação e manutenção deste backup são de sua responsabilidade. (Veja mais na Política de Privacidade)."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "4. Propriedade intelectual",
                items = listOf(
                    TermsListItem(
                        content = "Todo o software, design, marca e funcionalidades do aplicativo Lello são propriedade de seus desenvolvedores. Você recebe uma licença limitada, não exclusiva e intransferível para usar o App em seu dispositivo pessoal, de acordo com estes Termos."
                    ),
                    TermsListItem(
                        content = "No entanto, reiteramos: todo o conteúdo e dados inseridos por você no App pertencem exclusivamente a você."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "5. Alterações nos termos",
                items = listOf(
                    TermsListItem(
                        content = "Estes Termos podem ser atualizados periodicamente. Quando fizermos alterações significativas, você será notificado dentro do próprio aplicativo, dando-lhe a oportunidade de revisar as mudanças antes que entrem em vigor."
                    )
                )
            )

            Text(
                text = "Políticas de privacidade",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimension.spacingExtraLarge)
            )
            SettingsTermsAndPrivacyListSection(
                title = "1. Nosso compromisso com a privacidade",
                items = listOf(
                    TermsListItem(
                        content = "Sua privacidade é a base fundamental do Lello. Esta política explica quais informações coletamos (e, mais importante, quais não coletamos), como as usamos e como as protegemos."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "2. Informações que NÃO coletamos",
                items = listOf(
                    TermsListItem(
                        content = "Nós não temos e nunca teremos acesso ao conteúdo dos seus diários."
                    ),
                    TermsListItem(
                        content = "Não coletamos, armazenamos ou processamos nenhuma informação pessoal identificável inserida em seus registros, como seu nome, e-mail (exceto para autenticação opcional do Google Drive, que é gerenciada pelo Google), ou qualquer dado sensível que você escreve."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "3. Informações que coletamos",
                items = listOf(
                    TermsListItem(
                        content = "Para manter o aplicativo estável e funcionando, coletamos um volume mínimo de dados técnicos, estritamente anônimos:"
                    ),
                    TermsListItem(
                        title = "Dados de falhas (crashlytics)",
                        content = "Usamos um serviço do Firebase (Google) chamado Crashlytics. Se o aplicativo travar ou fechar inesperadamente, este serviço nos envia um relatório anônimo sobre o erro. Este relatório não inclui nenhum dado pessoal ou conteúdo dos seus diários; ele apenas nos informa sobre o problema técnico (modelo do dispositivo, versão do sistema operacional, e a linha de código que falhou) para que possamos corrigi-lo."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "4. Armazenamento de dados: totalmente no seu dispositivo",
                items = listOf(
                    TermsListItem(
                        content = "O design do Lello é centrado na privacidade local."
                    ),
                    TermsListItem(
                        title = "Armazenamento padrão",
                        content = "Por padrão, todos os seus dados, diários e registros são armazenados exclusivamente no armazenamento seguro do seu dispositivo. Eles não são enviados para nenhum servidor externo e não são acessíveis por nós ou por terceiros."
                    ),
                    TermsListItem(
                        title = "Sem acesso do desenvolvedor",
                        content = "A equipe do Lello não pode ver, acessar, extrair ou ler qualquer conteúdo que você cria."
                    ),
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "5. Backup (opcional): sincronização com google drive",
                items = listOf(
                    TermsListItem(
                        content = "Você tem a opção de ativar o backup dos seus dados no seu Google Drive pessoal."
                    ),
                    TermsListItem(
                        title = "É opcional",
                        content = "O aplicativo funciona perfeitamente sem isso. Você só ativa se desejar uma camada extra de segurança contra perda de dados (por exemplo, se trocar de celular)."
                    ),
                    TermsListItem(
                        title = "É privado",
                        content = "O backup é salvo em uma pasta dentro da sua conta pessoal do Google. Nós não temos acesso ao seu Google Drive."
                    ),
                    TermsListItem(
                        title = "É seguro",
                        content = "Para garantir a privacidade mesmo no seu backup, todos os seus dados são criptografados com o algoritmo sha-256 antes de saírem do seu dispositivo. Isso significa que, mesmo que alguém acessasse seu arquivo de backup no Google Drive, não conseguiria ler o conteúdo."
                    ),
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "6. Compartilhamento de informações",
                items = listOf(
                    TermsListItem(
                        content = "Nós não vendemos, alugamos ou compartilhamos suas informações pessoais com ninguém."
                    ),
                    TermsListItem(
                        content = "Como não temos acesso aos seus dados de diário, não temos o que compartilhar. Os únicos dados que processamos (dados anônimos de falhas) não são compartilháveis e são usados apenas para fins internos de desenvolvimento e estabilidade."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "7. Segurança dos seus dados",
                items = listOf(
                    TermsListItem(
                        content = "Implementamos medidas de segurança para proteger seus dados dentro do aplicativo:"
                    ),
                    TermsListItem(
                        title = "Acesso por Autenticação",
                        content = "O Lello solicita sua senha ou biometria (impressão digital, reconhecimento facial) para abrir o aplicativo e para executar ações sensíveis, impedindo o acesso não autorizado caso alguém pegue seu dispositivo."
                    ),
                    TermsListItem(
                        title = "Criptografia de backup",
                        content = "Conforme mencionado, os backups opcionais são criptografados."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "8. Gerenciamento dos seus dados",
                items = listOf(
                    TermsListItem(
                        content = "Você tem controle sobre os dados que insere. Você pode modificar e adicionar informações aos seus diários conforme julgar necessário."
                    ),
                    TermsListItem(
                        content = "Note que, por uma decisão de design focada na integridade do registro terapêutico (similar a um diário físico), os diários, uma vez criados, ficam permanentemente armazenados no aplicativo e não podem ser excluídos."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "9. Privacidade infantil",
                items = listOf(
                    TermsListItem(
                        title = "",
                        content = "O Lello não é destinado a menores de 16 anos. Não coletamos intencionalmente informações de indivíduos abaixo dessa idade."
                    )
                )
            )
            SettingsTermsAndPrivacyListSection(
                title = "10. Contato",
                items = listOf(
                    TermsListItem(
                        content = "Se você tiver alguma dúvida sobre esta Política de Privacidade ou Termos de Uso, entre em contato conosco."
                    ),
                    TermsListItem(
                        content = "Obrigado por confiar no Lello!"
                    )
                )
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Atualizado em: ")
                    }
                    append("15 de novembro de 2025")
                },
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SettingsTermsAndPrivacyTopAppBar(
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Uso e Privacidade"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
fun SettingsTermsAndPrivacyListSection(
    title: String? = null,
    items: List<TermsListItem>,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimension.spacingRegular)
            )
        }

        items.forEachIndexed { index, item ->
            Text(
                text = buildAnnotatedString {
                    if (item.title.isNotBlank()) {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(item.title)
                            if (!item.title.endsWith(":")) append(":")
                            append(" ")
                        }
                    }
                    append(item.content)
                },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (index == items.lastIndex)
                            Dimension.spacingExtraLarge
                        else
                            Dimension.spacingMedium
                    )
            )
        }
    }
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsTermsAndPrivacyScreenPreview_LightMode() {
    LelloTheme {
        SettingsTermsAndPrivacyScreenContent(
            onBack = {}
        )
    }
}

// endregion Previews