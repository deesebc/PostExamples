<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
  <fo:layout-master-set>
    <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm"
      margin-right="2cm">
      <fo:region-body/>
    </fo:simple-page-master>
  </fo:layout-master-set>
  <fo:page-sequence master-reference="simpleA4">
    <fo:flow flow-name="xsl-region-body">
      <fo:block font-size="20pt" font-family="sans-serif">Introduction</fo:block>
      <fo:block space-before="5mm" space-after="5mm">
        <fo:inline font-weight="bold">Hello</fo:inline>
        <fo:inline font-style="italic">World!</fo:inline>
      </fo:block>
    </fo:flow>
  </fo:page-sequence>
</fo:root>