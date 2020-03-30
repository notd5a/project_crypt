package com.gmail.ib.projectCrypt.newsPage;

import com.gmail.ib.projectCrypt.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import io.cryptocontrol.cryptonewsapi.CryptoControlApi;
import io.cryptocontrol.cryptonewsapi.models.Article;

import java.util.ArrayList;
import java.util.List;

@Route(value = "News", layout = MainLayout.class)
@PageTitle("News")
public class newsView extends VerticalLayout {

    private CryptoControlApi api = new CryptoControlApi("6ca9b0ccc7e64df918c21426e1c832b0");

    public newsView() {

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Latest Cryptocurrency News");
        title.getElement().getStyle().set("color", "rgb(18, 202, 214)");
        add(title);

        H2 topNews = new H2("Top News");
        add(topNews);

        List<news> newsArticles = new ArrayList<>();

        //Store what u need into the ArrayList here
        api.getTopNews(new CryptoControlApi.OnResponseHandler<List<Article>>() {
            public void onSuccess(List<Article> body) {
                for (Article article : body) {
                    newsArticles.add(new news(article.getTitle(), article.getDescription(), article.getSourceDomain()));
                }
            }

            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        //Initialize the grid and load it into the view
        Grid<news> newsGrid = new Grid<>(news.class);
        newsGrid.setItems(newsArticles);

        newsGrid.setSelectionMode(Grid.SelectionMode.NONE);
        newsGrid.setItemDetailsRenderer(TemplateRenderer.<news>of(
                "<div class='details' style=''>" +
                        "<div> <p>[[item.body]] <br> </br> Read more on [[item.source]] </p> </div>" +
                        "</div>"
        )
                .withProperty("body", news::getBody)
                .withProperty("source", news::getSource)
                .withEventHandler("handleClick", news -> {
                    newsGrid.getDataProvider().refreshItem(news);
        }));

        newsGrid.setSizeFull();
        newsGrid.setHeightFull();
        newsGrid.setHeightByRows(true);
        newsGrid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS, GridVariant.LUMO_NO_BORDER);
        newsGrid.setColumns("title", "body", "source");

        add(newsGrid);

    }

}
