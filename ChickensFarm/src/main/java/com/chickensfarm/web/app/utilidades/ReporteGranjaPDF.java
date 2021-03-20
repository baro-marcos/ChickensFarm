package com.chickensfarm.web.app.utilidades;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.chickensfarm.web.app.models.Farm;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("granjaseleccionada") // para que cuando detecte la ruta genere el pdf
public class ReporteGranjaPDF extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Farm granja = (Farm) model.get("granjaSelec");

		// Armamos el documento
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(-10, -10, 40, 20);
		document.open();
		PdfPTable tablaTitulo = new PdfPTable(1);
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 21, Color.BLUE);
		PdfPCell celdaTitulo = new PdfPCell(new Phrase("REPORTE GRANJA", fuenteTitulo));
		celdaTitulo.setBorder(0);
		celdaTitulo.setBackgroundColor(new Color(50, 150, 120));
		celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaTitulo.setPadding(40);
		tablaTitulo.addCell(celdaTitulo);
		tablaTitulo.setSpacingAfter(40);

		// Tabla info
		Font fuenteTituloCol = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.BLUE);
		Font fuenteCelda = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Color.BLACK);
		PdfPTable tablaDatosGranja = new PdfPTable(6); // 6 cols
		PdfPCell celda = null;
		tablaDatosGranja.setWidths(new float[] { 1f, 3.5f, 2f, 2.5f, 2.5f, 3.5f });

		celda = new PdfPCell(new Phrase("ID", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("FECHA", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("HUEVOS", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("GALLINAS", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("IMPORTE TOTAL GRANJA", fuenteTituloCol));
		celda.setBackgroundColor(Color.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase(granja.getIdGranja().toString(), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase(granja.getNombreGranja(), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase(granja.getFechaGranja().toString(), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase(String.valueOf(granja.getCantidadHuevos()), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase(String.valueOf(granja.getCantidadGallinas()), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		celda = new PdfPCell(new Phrase("$ " + granja.getImporteTotalGranja().toString(), fuenteCelda));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		tablaDatosGranja.addCell(celda);

		// Agregamos las tablas al documento
		document.add(tablaTitulo);
		document.add(tablaDatosGranja);

	}

}
